package com.book_catalog_web.validator.annotation;

import com.book_catalog_web.validator.PastDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PastDateValidator.class)
public @interface PastDate {
    String message() default "date must be past";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

}
