package com.hrm.test.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({PARAMETER})
@Retention(RUNTIME)
public @interface ValidAge {

    String message() default "Invalid Age";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
