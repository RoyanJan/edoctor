package com.outwit.edoctor.infrastructure.Term;

import lombok.Data;

@Data
public class Interaction {

    private StatusCode statusCode;
    private String Message;
    private Object content;

    public Interaction(StatusCode statusCode, String message, Object content) {
        this.statusCode = statusCode;
        Message = message;
        this.content = content;
    }

    public Interaction(StatusCode statusCode, String message) {
        this(statusCode, message, null);
    }

    public Interaction(StatusCode statusCode) {
        this(statusCode, "", null);
    }

    public String getStatusCode() {
        return Integer.toString(statusCode.getNumber());
    }

}
