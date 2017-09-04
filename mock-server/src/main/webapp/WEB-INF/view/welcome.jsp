<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8">
<link rel="stylesheet"
      href="https://unpkg.com/bootstrap@3.3.7/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../scripts/mock-js.js" type="text/javascript"></script>      

</head>
<body>

	<div class="container">
		<form id="mockForm" class="form-horizontal" method="post">
	        <div class="form-group">
	        	<h1>Welcome to Mock-Server!</h1>
	        	
	        	<div id="result" class="hidden">
	        	  <div class="panel panel-success">
				      <div class="panel-heading">Your mock link is ready!</div>
				      <div class="panel-body"></div>
				  </div>
				</div>
	        	
	        	<div class="row">
				    <div class="col-sm-6" >
				    	<label class="center-block">Status Code:</label>
		     			<select id="statusCodes" class="form-control" name="statusCode"></select>
				    </div>
				    <div class="col-sm-6" >
				    	<label class="center-block">Content Type:</label>
			   			<select id="ctypes" class="form-control" name="contentType"></select>
				    </div>
				</div>
				
				<br>
				
				<div class="row">
				    <div class="col-sm-6" >
				    	<label class="center-block">Encoding:</label>
			    		<select id="encodings" class="form-control" name="encoding"></select>
				    </div>
				    <div class="col-sm-6" >
				    	<label class="center-block">Delay (in ms):</label>
			   			<input type="number" id="delay" value=0 class="form-control" name="delay"></input>
				    </div>
				</div>
				
				<br>
			    
			    <label class="form-inline">Headers:</label>
			    <div class="form-inline headersTemplate">
		            <input type="text" class="form-control" name="headers[0][name]" placeholder="Header Name" /> :
		            <input type="text" class="form-control" name="headers[0][value]" placeholder="Header Value" />
		            <button type="button" class="btn btn-success addButton">Add</button>
			    </div>
			    
			    <br>
				
				<label class="center-block">Body:</label>
			    <textarea rows="5" class="form-control" name="body"></textarea>
			    
			</div>
			
			<button type="submit" class="btn btn-primary form-group" >Submit</button>
			
			
			
			<div id="errorResult" class="hidden form-group">
			  <h2>Uh-oh! Can't connect to the server!</h2>
			</div>
		
		</form>
    </div>
</body>
</html>