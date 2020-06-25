package com.mockapi.service;

import com.mockapi.dto.MockResponseDTO;
import com.mockapi.dto.RequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MockService {
    public ResponseEntity<Object> getMockResponse(String mockId);
    public ResponseEntity<Object> saveMockResponse(MockResponseDTO request);
    public ResponseEntity<Object> deleteMockResponse(MockResponseDTO request);
    public ResponseEntity<Object> testDTOEndpoint(RequestDTO request);
    public ResponseEntity<Object> testRequestMockId(String mockId);
    public ResponseEntity<Object> deleteMockRequest(RequestDTO request);
    public ResponseEntity<Object> saveMockRequest(RequestDTO requestDTO);
}
