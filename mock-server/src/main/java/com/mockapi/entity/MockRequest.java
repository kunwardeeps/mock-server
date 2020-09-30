package com.mockapi.entity;

import com.mockapi.dto.HeaderDTO;
import org.springframework.data.annotation.Id;

import java.util.List;

public class MockRequest {
    @Id
    private String mockID;
    private String hostName;
    private String endpoint;
    private String schema;
    private String httpMethod;
    private Object body;
    private List<HeaderDTO> headers;

    public String getMockID() {
        return mockID;
    }

    public void setMockID(String mockID) {
        this.mockID = mockID;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public List<HeaderDTO> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HeaderDTO> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "MockRequest{" +
                "mockID='" + mockID + '\'' +
                ", hostName='" + hostName + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", schema='" + schema + '\'' +
                ", httpMethod=" + httpMethod +
                ", body=" + body +
                ", headers=" + headers +
                '}';
    }

}
