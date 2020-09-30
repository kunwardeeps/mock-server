package com.mockapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface MockResponseService {
    Mono<ResponseEntity<Object>> testMockResponse(String mockId);
}
