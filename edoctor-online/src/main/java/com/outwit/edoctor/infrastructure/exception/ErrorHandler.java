package com.outwit.edoctor.infrastructure.exception;

import com.outwit.edoctor.infrastructure.Term.Interaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Interaction errorHandler(Throwable ex) {
        ApplicationException applicationException = null;
        if (ex instanceof ApplicationException) {
            applicationException = (ApplicationException) ex;
        } else {
            applicationException = new ApplicationException(ex.getMessage(), ex, SystemCode.INTERNAL_ERROR);
        }
        log.error(applicationException.getMessage());
        return new Interaction(applicationException.getStatusCode(), messageSource.getMessage(String.valueOf(applicationException.getStatusCode()), null, null));
    }
}
