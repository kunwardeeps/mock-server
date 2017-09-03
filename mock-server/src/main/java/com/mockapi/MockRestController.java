package com.mockapi;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mockapi.dto.HeaderDTO;
import com.mockapi.dto.MockRequestDTO;
import com.mockapi.entity.MockRequest;
import com.mockapi.repository.NoSQLRepository;

@RestController
public class MockRestController {
	
	@Autowired
	private NoSQLRepository repository;
	
    @RequestMapping(value = "/mock/{mockId}")
    public ResponseEntity<String> mockRequest(@PathVariable String mockId) {
    	
    	MockRequest entity = repository.getByMockId(mockId);
    	
    	MultiValueMap<String, String> headers = getHeadersFromEntity(entity);

    	HttpStatus httpStatus = HttpStatus.valueOf(entity.getStatusCode());
    	
    	return new ResponseEntity<String>(entity.getBody(), headers, httpStatus);
    }

	@RequestMapping(value = "/save", method = RequestMethod.POST)
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> save(@RequestBody MockRequestDTO request) {
    	
    	MockRequest entity = getEntityFromDTO(request);
    	
    	repository.save(entity);
    	
    	String url = new StringBuilder("http://localhost:8080/")
    			.append("mock/")
    			.append(entity.getMockId())
    			.toString();
    	
    	return new ResponseEntity<String>(url, HttpStatus.OK);
    }
    
    private MultiValueMap<String, String> getHeadersFromEntity(MockRequest entity) {
    	MultiValueMap<String, String> headers = new HttpHeaders();
    	
    	for (HeaderDTO header: entity.getHeaders()){
    		headers.add(header.getName(), header.getValue());
    	}
    	
    	if (StringUtils.isNotEmpty(entity.getContentType())){
    		headers.add("Content-Type", entity.getContentType());
    		headers.add("Accept-Encoding", entity.getEncoding());
    	}
		return headers;
	}

	private MockRequest getEntityFromDTO(MockRequestDTO request) {
		
		MockRequest entity = new MockRequest();
		ObjectId id = new ObjectId();

    	entity.setMockId(id.toString());
    	entity.setBody(request.getBody());
    	entity.setContentType(request.getContentType());
    	entity.setEncoding(request.getEncoding());
    	entity.setStatusCode(request.getStatusCode());
    	entity.setHeaders(request.getHeaders());
    	
    	return entity;
	}

}