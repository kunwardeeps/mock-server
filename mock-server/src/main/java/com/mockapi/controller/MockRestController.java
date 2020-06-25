package com.mockapi.controller;

import com.mockapi.dto.MockResponseDTO;
import com.mockapi.dto.RequestDTO;
import com.mockapi.service.MockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MockRestController {

	@Autowired
	private MockService mockService;

	public static final Logger LOGGER = LoggerFactory.getLogger(MockRestController.class);

    @GetMapping(value = "/mock/{mockId}")
    public ResponseEntity<Object> mockRequestGet(@PathVariable String mockId) {
        LOGGER.info("Received Request at mock endpoint /mock/{}", mockId);
		return mockService.getMockResponse(mockId);
    }

    @PostMapping(value = "/mock/{mockId}")
    public ResponseEntity<Object> mockRequestPost(@PathVariable String mockId, @RequestBody Object o) {

        LOGGER.info("Received Request at mock endpoint /mock/{}", mockId);
        return mockService.getMockResponse(mockId);
    }

	@PostMapping(value = "/save/response")
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> saveMock(@RequestBody MockResponseDTO request) {
        LOGGER.info("Got mock {} to be saved to DB", request);
    	return mockService.saveMockResponse(request);
    }

    @PostMapping(value = "/delete/response")
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> deleteMock(@RequestBody MockResponseDTO request) {
        LOGGER.info("Got mock {} to be deleted from DB", request);
        return mockService.deleteMockResponse(request);
    }

    @PostMapping(value = "/test")
    public ResponseEntity<Object> test(@RequestBody RequestDTO requestDTO){
        LOGGER.info("testing endpoint: {}/{}", requestDTO.getHostName(), requestDTO.getEndpoint());
        return mockService.testDTOEndpoint(requestDTO);
    }

    @PostMapping(value = "/save/request")
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> saveMockRequest(@RequestBody RequestDTO request) {
        LOGGER.info("Got mock {} to be saved to DB", request);
        return mockService.saveMockRequest(request);
    }

    @PostMapping(value = "/delete/request")
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> deleteMock(@RequestBody RequestDTO request) {
        LOGGER.info("Got mock {} to be deleted from DB", request);
        return mockService.deleteMockRequest(request);
    }

    @GetMapping(value = "/test/{mockId}")
    public ResponseEntity<Object> testMockId(@PathVariable String mockId){
        LOGGER.info("testing mockID {} ", mockId);
        return mockService.testRequestMockId(mockId);
    }



}