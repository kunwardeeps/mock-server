package com.mockapi.controller;

import com.mockapi.service.MockService;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private MockService mockService;

	public static final Logger LOGGER = LoggerFactory.getLogger(MockRestController.class);

    @RequestMapping(value = "/mock/{mockId}")
    public ResponseEntity<Object> mockRequest(@PathVariable String mockId) {
        LOGGER.info("Received Request at mock endpoint /mock/{}", mockId);
		return mockService.getMockRequest(mockId);
    }

	@RequestMapping(value = "/save", method = RequestMethod.POST)
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> save(@RequestBody MockRequestDTO request) {
        LOGGER.info("Got mock {} to be saved to DB", request);
    	return mockService.saveMockRequest(request);
    }

}