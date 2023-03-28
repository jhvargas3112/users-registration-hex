package com.company.signup.domain.model.user;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = LegalAgeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LegalAge   {

  String message() default "Legal age must be 18";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  int value();

}
