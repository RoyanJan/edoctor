package com.outwit.edoctor.infrastructure.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FixLengthValidator implements ConstraintValidator<FixLength, String> {

    private int length;

    @Override
    public void initialize(FixLength constraintAnnotation) {
        this.length = constraintAnnotation.length();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() == length;
    }
}
