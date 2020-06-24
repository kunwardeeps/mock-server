package com.mockapi.service;

import com.mockapi.dto.MockRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MockService {
    public ResponseEntity<Object> getMockRequest(String mockId);
    public ResponseEntity<Object> saveMockRequest(MockRequestDTO request);
}
