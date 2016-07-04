package com.outwit.edoctor.infrastructure.exception;

import java.util.Map;

public class ApplicationException extends RuntimeException {
    private ErrorCode errorCode;
    private Map<String, Object> properties;

    public ApplicationException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public ApplicationException setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
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
