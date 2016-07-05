package com.outwit.edoctor.infrastructure.exception;

import com.outwit.edoctor.infrastructure.Term.Interaction;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Interaction errorHandler(Throwable ex){
        ApplicationException applicationException = null;
        return new Interaction(SystemCode.INTERNAL_ERROR);
    }
}
