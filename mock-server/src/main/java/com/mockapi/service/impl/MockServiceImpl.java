package com.mockapi.service.impl;

import com.mockapi.dto.HeaderDTO;
import com.mockapi.dto.MockRequestDTO;
import com.mockapi.entity.MockRequest;
import com.mockapi.repository.NoSQLRepository;
import com.mockapi.service.MockService;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class MockServiceImpl implements MockService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MockServiceImpl.class);

    @Autowired
    private NoSQLRepository repository;

    @Autowired
    Environment environment;

    @Override
    public ResponseEntity<Object> getMockRequest(String mockId) {
        MockRequest entity = repository.getByMockId(mockId);
        if (entity != null) {
            LOGGER.debug("Got entity {} for mockID {} ", entity.toString(), mockId);
            MultiValueMap<String, String> headers = getHeadersFromEntity(entity);
            HttpStatus httpStatus = HttpStatus.valueOf(entity.getStatusCode());
            LOGGER.debug("Entering Delay period of {}", entity.getDelay());
            if (entity.getDelay() > 0){
                try {
                    Thread.sleep(entity.getDelay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return new ResponseEntity<Object>(entity.getBody(), headers, httpStatus);
        } else {
            LOGGER.error("No mock Response found for {}", mockId);
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }

    private MultiValueMap<String, String> getHeadersFromEntity(MockRequest entity) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        for (HeaderDTO header: entity.getHeaders()){
            headers.add(header.getName(), header.getValue());
        }

        if (StringUtils.isNotEmpty(entity.getContentType())){
            headers.add("Content-Type", entity.getContentType());
            headers.add("Accept-Encoding", entity.getEncoding());
        }
        LOGGER.debug("Headers length {} for entity {}",headers.size(),entity.getMockId());
        return headers;
    }

    @Override
    public ResponseEntity<Object> saveMockRequest(MockRequestDTO request) {
        MockRequest entity = getEntityFromDTO(request);

        LOGGER.debug("Saving entity {}", entity.toString());
        repository.save(entity);

        String url = null;
        try {
            url = "http://" + InetAddress.getLocalHost().getHostName() + ":" + environment.getProperty("server.port") +
                    "mock/" +
                    entity.getMockId();
        } catch (UnknownHostException e) {
            LOGGER.error("Unable to resolve current hostname ");
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(url, HttpStatus.OK);
    }

    private MockRequest getEntityFromDTO(MockRequestDTO request) {

        MockRequest entity = new MockRequest();

        if (request.getMockId() == null) {
            LOGGER.debug("No mockID passed in request body, generating mockID");
            ObjectId id = new ObjectId();
            LOGGER.info("Generated MockId: {}", id.toString());
            entity.setMockId(id.toString());
        } else entity.setMockId(request.getMockId());

        entity.setBody(request.getBody());
        entity.setContentType(request.getContentType());
        entity.setEncoding(request.getEncoding());
        entity.setStatusCode(request.getStatusCode());
        entity.setHeaders(request.getHeaders());
        entity.setDelay(request.getDelay());

        return entity;
    }
}
