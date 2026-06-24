package com.book_catalog_web.validator;

import com.book_catalog_web.validator.annotation.PastDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PastDateValidator implements ConstraintValidator<PastDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return false;
    }
}
