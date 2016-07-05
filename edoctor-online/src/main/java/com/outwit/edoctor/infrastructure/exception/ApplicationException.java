package com.outwit.edoctor.infrastructure.exception;

import com.outwit.edoctor.infrastructure.Term.StatusCode;

import java.util.Map;

public class ApplicationException extends RuntimeException {
    private StatusCode statusCode;
    private Map<String, Object> properties;

    public ApplicationException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public ApplicationException setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public <T> T get(String key) {
        return (T) this.properties.get(key);
    }

    public ApplicationException set(String key, Object value) {
        this.properties.put(key, value);
        return this;
    }
}
