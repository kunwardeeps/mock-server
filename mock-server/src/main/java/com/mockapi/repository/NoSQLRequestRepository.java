package com.mockapi.repository;

import com.mockapi.entity.MockRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoSQLRequestRepository extends MongoRepository<MockRequest, String> {

    public MockRequest getByMockID(String mockId);
}
