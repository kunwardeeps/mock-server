package com.mockapi.repository;

import com.mockapi.entity.MockRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface NoSQLRequestRepository extends ReactiveMongoRepository<MockRequest, String> {

    Mono<MockRequest> getByMockID(String mockId);
}
