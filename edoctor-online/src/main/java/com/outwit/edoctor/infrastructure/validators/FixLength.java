package com.outwit.edoctor.infrastructure.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FixLengthValidator.class)
public @interface FixLength {

    int length();

    String message() default "FixLength length is not correct .";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
