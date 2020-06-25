package com.mockapi.entity;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;

import com.mockapi.dto.HeaderDTO;

public class MockResponse {
	
	@Id
    private String mockId;
	
	private int statusCode;
	private String contentType;
	private String encoding;
	private Object body;
	private List<HeaderDTO> headers;
	private int delay;
	
	public String getMockId() {
		return mockId;
	}
	public void setMockId(String mockId) {
		this.mockId = mockId;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}

	@Override
	public String toString() {
		return "MockResponse{" +
				"mockId='" + mockId + '\'' +
				", statusCode=" + statusCode +
				", contentType='" + contentType + '\'' +
				", encoding='" + encoding + '\'' +
				", body=" + body +
				", headers=" + headers +
				", delay=" + delay +
				'}';
	}
}