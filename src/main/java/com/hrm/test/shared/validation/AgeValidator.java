package com.hrm.test.shared.validation;

import com.hrm.test.business.entity.Employee;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeValidator implements ConstraintValidator<ValidAge, Employee> {

    private final static String PATTERN_MAX = "yyMMdd";

    @Override
    public void initialize(ValidAge constraintAnnotation) {

    }

    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext context) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_MAX);
            LocalDate birthDate = LocalDate.parse(employee.getNationalId().substring(1, 7), formatter);
            LocalDate now = LocalDate.now();

            //1900 and 2000 parsing issue
            if (birthDate.isAfter(now)) {
                birthDate = birthDate.minusYears(100);
            }

            Period period = birthDate.until(now);
            int years = period.getYears();
            return years == employee.getAge();
        } catch (Exception ex) {
            return false;
        }
    }


}
