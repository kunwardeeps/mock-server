package com.mockapi.service.impl;

import com.mockapi.repository.NoSQLResponseRepository;
import com.mockapi.service.MockResponseService;
import com.mockapi.utils.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

@Service
public class MockResponseServiceImpl implements MockResponseService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MockResponseServiceImpl.class);

    @Autowired
    private NoSQLResponseRepository responseRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    public MockResponseServiceImpl(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

    @Override
    public Mono<ResponseEntity<Object>> testMockResponse(String mockId) {
        return responseRepository.getByMockId(mockId)
                .flatMap(mockResponse -> {
                    LOGGER.debug("Got entity {} for mockID {} ", mockResponse, mockId);
                    MultiValueMap<String, String> headers = serviceUtils.getHeadersFromEntity(mockResponse);
                    HttpStatus httpStatus = HttpStatus.valueOf(mockResponse.getStatusCode());
                    LOGGER.debug("Entering Delay period of {}", mockResponse.getDelay());
                    if (mockResponse.getDelay() > 0){
                        try {
                            Thread.sleep(mockResponse.getDelay());
                        } catch (InterruptedException e) {
                            LOGGER.error("Thread Interrupted {}", Thread.currentThread());
                            Thread.currentThread().interrupt();
                        }
                    }
                    return Mono.justOrEmpty(new ResponseEntity<>(mockResponse.getBody(), headers, httpStatus));
                })
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
