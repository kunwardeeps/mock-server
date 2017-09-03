package com.mockapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mockapi.entity.MockRequest;

public interface NoSQLRepository extends MongoRepository<MockRequest, String> {
	
	public MockRequest getByMockId(String mockId);

}
