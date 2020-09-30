package com.mockapi.service;

import com.mockapi.dto.MockResponseDTO;
import com.mockapi.dto.RequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MockService {
    ResponseEntity<Object> testMockResponse(String mockId);
    ResponseEntity<Object> saveMockResponse(MockResponseDTO request);
    ResponseEntity<Object> deleteMockResponse(MockResponseDTO request);
    ResponseEntity<Object> testDTOEndpoint(RequestDTO request);
    ResponseEntity<Object> testRequestMockId(String mockId);
    ResponseEntity<Object> deleteMockRequest(RequestDTO request);
    ResponseEntity<Object> saveMockRequest(RequestDTO requestDTO);
}
