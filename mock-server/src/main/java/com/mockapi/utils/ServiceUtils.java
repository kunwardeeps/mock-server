package com.mockapi.utils;

import com.mockapi.dto.HeaderDTO;
import com.mockapi.dto.MockResponseDTO;
import com.mockapi.dto.RequestDTO;
import com.mockapi.entity.MockRequest;
import com.mockapi.entity.MockResponse;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
public class ServiceUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtils.class);

    public MockResponse getResponseEntityFromDTO(MockResponseDTO request) {

        MockResponse entity = new MockResponse();

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

    public MultiValueMap<String, String> getHeadersFromEntity(MockResponse entity) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        for (HeaderDTO header: entity.getHeaders()){
            headers.add(header.getName(), header.getValue());
        }

        if (StringUtils.isNotEmpty(entity.getContentType())){
            headers.add("Content-Type", entity.getContentType());
        }
        if (StringUtils.isNotEmpty(entity.getEncoding())){
            headers.add("Accept-Encoding", entity.getEncoding());
        }
        LOGGER.debug("Headers length {} for entity {}",headers.size(),entity.getMockId());
        return headers;
    }

    public URI generateUri(String hostname, String endPoint, String schema){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(schema).host(hostname).path(endPoint).build();
        return uriComponents.toUri();
    }

    public MockRequest getMockRequestFromDTO(RequestDTO requestDTO){
        MockRequest mockRequest = new MockRequest();
        mockRequest.setBody(requestDTO.getBody());
        List<HeaderDTO> headerDTOList = requestDTO.getHeaders();
        if (StringUtils.isNotEmpty(requestDTO.getEncoding())){
            headerDTOList.add(new HeaderDTO("Accept-Encoding", requestDTO.getEncoding()));
        }
        if (StringUtils.isNotEmpty(requestDTO.getContentType())){
            headerDTOList.add(new HeaderDTO("Content-Type", requestDTO.getContentType()));
        }
        if (StringUtils.isNotEmpty(requestDTO.getAccept())){
            headerDTOList.add(new HeaderDTO("Accept", requestDTO.getAccept()));
        }
        mockRequest.setEndpoint(requestDTO.getEndpoint());
        mockRequest.setHostName(requestDTO.getHostName());
        mockRequest.setHeaders(headerDTOList);
        mockRequest.setSchema(requestDTO.getSchema());
        mockRequest.setMockID(requestDTO.getMockID());
        mockRequest.setHttpMethod(requestDTO.getHttpMethod());
        return mockRequest;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
