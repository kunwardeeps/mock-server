package com.mockapi.repository;

import com.mockapi.entity.MockResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoSQLResponseRepository extends MongoRepository<MockResponse, String> {
	
	public MockResponse getByMockId(String mockId);

}
