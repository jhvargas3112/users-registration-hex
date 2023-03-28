package com.company.signup.api.validators;

import java.time.LocalDate;
import java.time.Period;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LegalAgeValidator implements ConstraintValidator<LegalAge, LocalDate> {

  private Integer years;

  public void initialize(LegalAge legalAge) {
    years = legalAge.value();
  }

  @Override
  public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {

    return Period.between(birthDate, LocalDate.now()).getYears() >= years;

  }

}
