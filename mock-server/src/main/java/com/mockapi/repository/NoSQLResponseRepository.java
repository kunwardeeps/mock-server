package com.mockapi.repository;

import com.mockapi.entity.MockResponse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface NoSQLResponseRepository extends ReactiveMongoRepository<MockResponse, String> {

	Mono<MockResponse> getByMockId(String mockId);

}
