package com.mockapi.controller;

import com.mockapi.dto.MockResponseDTO;
import com.mockapi.entity.MockResponse;
import com.mockapi.repository.NoSQLResponseRepository;
import com.mockapi.service.MockResponseService;
import com.mockapi.utils.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/response")
public class MockRestControllerV2 {

    @Autowired
    private MockResponseService mockResponseService;

    @Autowired
    private NoSQLResponseRepository responseRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    public static final Logger LOGGER = LoggerFactory.getLogger(MockRestControllerV2.class);

    @GetMapping
    public Flux<MockResponse> getResponses(){
        LOGGER.info("Getting all responses from DB");
        return responseRepository.findAll();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MockResponse> getResponsesStream(){
        LOGGER.info("Getting all responses from DB");
        return responseRepository.findAll();
    }

    @GetMapping("/{mockId}")
    public Mono<ResponseEntity<MockResponse>> getResponseById(@PathVariable String mockId){
        LOGGER.info("Fetching {} from DB",mockId);
        return responseRepository.findById(mockId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{mockId}")
    public Mono<ResponseEntity<Object>> deleteResponseById(@PathVariable String mockId){
        LOGGER.info("Deleting {} from DB",mockId);
        return responseRepository.findById(mockId)
                .flatMap(mockResponseFromDb -> responseRepository.delete(mockResponseFromDb)
                        .then(Mono.just(new ResponseEntity<Object>("{\"message\":\"Deleted\"}",HttpStatus.OK))))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<MockResponse>> create(@RequestBody MockResponseDTO responseDTO){
        MockResponse mockResponse = serviceUtils.getResponseEntityFromDTO(responseDTO);
        LOGGER.info("saving {} to DB", mockResponse);
        return responseRepository.save(mockResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.unprocessableEntity().build());
    }

    @PutMapping("/{mockId}")
    public Mono updateStudent(@PathVariable String mockId, @RequestBody MockResponseDTO responseDTO){
        MockResponse mockResponse = serviceUtils.getResponseEntityFromDTO(responseDTO);
        return responseRepository.findById(mockId)
                .flatMap(selectedResponseFromDB ->{
                    selectedResponseFromDB=mockResponse;
                    return responseRepository.save(selectedResponseFromDB);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/test/{mockId}", method = {RequestMethod.GET, RequestMethod.POST})
    public Mono<ResponseEntity<Object>> mockRequestTest(@PathVariable String mockId) {
        LOGGER.info("Received Request at mock endpoint /mock/{}", mockId);
        return mockResponseService.testMockResponse(mockId);
    }
}
