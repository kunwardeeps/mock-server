package com.mockapi.dto;

import java.util.List;

public class RequestDTO {

    private String mockID;
    private String hostName;
    private String endpoint;
    private String schema;
    private String httpMethod;
    private String contentType;
    private String encoding;
    private Object body;
    private List<HeaderDTO> headers;
    private String  accept;

    public String getMockID() {
        return mockID;
    }

    public void setMockID(String mockID) {
        this.mockID = mockID;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
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
        return "RequestDTO{" +
                "mockID='" + mockID + '\'' +
                ", hostName='" + hostName + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", schema='" + schema + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", contentType='" + contentType + '\'' +
                ", encoding='" + encoding + '\'' +
                ", body=" + body +
                ", headers=" + headers +
                ", accept='" + accept + '\'' +
                '}';
    }
}
