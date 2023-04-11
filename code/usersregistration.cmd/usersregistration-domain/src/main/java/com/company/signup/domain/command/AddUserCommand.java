package com.company.signup.domain.command;

import com.users.cqrs.core.commands.BaseCommand;
import com.users.resgistration.common.validators.LegalAge;
import java.time.LocalDate;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddUserCommand extends BaseCommand {

  @NotBlank
  @Size(min = 8)
  String userName;

  @NotBlank
  String password;

  @NotNull
  @Past
  @LegalAge(value = 18)
  LocalDate birthDate;

  @NotNull
  @DecimalMin(value = "1.00")
  @DecimalMax(value = "2.30")
  Double height;

  @NotNull
  @Min(40)
  @Max(150)
  Double weight;

}
