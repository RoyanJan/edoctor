package com.outwit.edoctor.infrastructure.exception;

import com.outwit.edoctor.infrastructure.Term.Interaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ErrorHandler {

    @Autowired
    private MessageSource messageSource;

    // TODO 分类异常处理 （例如，shiro）

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Interaction unAunthorizedHandler(Exception e) {
        log.info(e.getMessage());
        return new Interaction(SystemCode.UNAUTHORIZED, messageSource.getMessage(String.valueOf(SystemCode.UNAUTHORIZED), null, null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Interaction parameterValidateHandler(Exception e) {
        log.info(e.getMessage());
        return new Interaction(SystemCode.PARAMETER_ERROR, messageSource.getMessage(String.valueOf(SystemCode.PARAMETER_ERROR), null, null));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Interaction errorHandler(Throwable ex) {
        ApplicationException applicationException = null;
        if (ex instanceof ApplicationException) {
            applicationException = (ApplicationException) ex;
        } else {
            applicationException = new ApplicationException(ex.getMessage(), ex, SystemCode.INTERNAL_ERROR);
        }
        log.info(applicationException.getMessage());
        return new Interaction(applicationException.getStatusCode(), messageSource.getMessage(String.valueOf(applicationException.getStatusCode()), null, null));
    }
}
