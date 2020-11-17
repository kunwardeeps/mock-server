$(document).ready(function() {
    var codes = [{key:"200 OK",val:200},
    			{key:"201 Created",val:201},
    			{key:"404 Not Found",val:404},
    			{key:"403 Forbidden",val:403},
    			{key:"400 Bad Request",val:400},
    			{key:"500 Internal Server Error",val:500},
    			{key:"501 Not Implemented",val:501},
    			{key:"502 Bad Gateway",val:502},
    			{key:"503 Service Unavailable",val:503}];
    var ctypes = ["application/json","application/xml","text/json","text/xml"];
    var encodings = ["UTF-8","UTF-16"];

    var option = '';
    for (var i = 0; i < codes.length; i++){
       option += '<option value="'+ codes[i].val + '">' + codes[i].key + '</option>';
    }
    $('#statusCodes').append(option);

    option = '';
    for (var i = 0; i < ctypes.length; i++){
       option += '<option value="'+ ctypes[i] + '">' + ctypes[i] + '</option>';
    }
    $('#ctypes').append(option);

    option = '';
    for (var i = 0; i < encodings.length; i++){
        option += '<option value="'+ encodings[i] + '">' + encodings[i] + '</option>';
     }
    $('#encodings').append(option);

    var wrapper = $(".headersTemplate"); //Fields wrapper
    var i = 0;
    $(".addButton").click(function(){
    	i++;
        $(wrapper).append('<div class="form-inline"><br><input type="text" class="form-control" name="headers['+i+'][name]"  placeholder="Header Name"/> : <input type="text" class="form-control" name="headers['+i+'][value]" placeholder="Header Value" /> <button type="button" class="btn btn-danger removeButton">Delete</button></div>'); //add input box
    });
    $(wrapper).on("click",".removeButton", function(){ //user click on remove text
        $(this).parent('div').remove();
    });

    (function($){
        $.fn.serializeObject = function(){

            var self = this,
                json = {},
                push_counters = {},
                patterns = {
                    "validate": /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
                    "key":      /[a-zA-Z0-9_]+|(?=\[\])/g,
                    "push":     /^$/,
                    "fixed":    /^\d+$/,
                    "named":    /^[a-zA-Z0-9_]+$/
                };


            this.build = function(base, key, value){
                base[key] = value;
                return base;
            };

            this.push_counter = function(key){
                if(push_counters[key] === undefined){
                    push_counters[key] = 0;
                }
                return push_counters[key]++;
            };

            $.each($(this).serializeArray(), function(){

                // skip invalid keys
                if(!patterns.validate.test(this.name)){
                    return;
                }

                var k,
                    keys = this.name.match(patterns.key),
                    merge = this.value,
                    reverse_key = this.name;

                while((k = keys.pop()) !== undefined){

                    // adjust reverse_key
                    reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

                    // push
                    if(k.match(patterns.push)){
                        merge = self.build([], self.push_counter(reverse_key), merge);
                    }

                    // fixed
                    else if(k.match(patterns.fixed)){
                        merge = self.build([], k, merge);
                    }

                    // named
                    else if(k.match(patterns.named)){
                        merge = self.build({}, k, merge);
                    }
                }

                json = $.extend(true, json, merge);
            });

            return json;
        };
    })(jQuery);

    $("#mockForm").submit(function(e) {
    	e.preventDefault();

    	var request = JSON.stringify($(this).serializeObject());

    	console.log(request);

        $.ajax({
                url: '/api/save/response',
                type: 'POST',
                contentType: "application/json",
                data: request,
                success: function(data) {
                	console.log('data: ' + data);
                	if(data != ""){
                        $("#result").removeClass("hidden");
                        $(".panel-body").replaceWith('<div class="panel-body"><a href="'+data+'" target="_blank">'+data+'</a> </div>');
                    }
                	else {
                		$("#errorResult").removeClass("hidden");
                	}
                },
    	        error : function(e) {
    				alert("Error!");
    				console.log("ERROR: ", e);
    			}
        });
    });
});