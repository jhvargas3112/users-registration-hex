package com.company.signup.domain.model.user;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class BodyMeasurements {

  @NotNull
  @DecimalMin(value = "1.00")
  @DecimalMax(value = "2.30")
  Double height;

  @NotNull
  @Min(40)
  @Max(150)
  Double weight;

  public static BodyMeasurements create(final Double height, final Double weight) {
    return new BodyMeasurements(height, weight);
  }

  public Double calculateBmi() {
    return weight / Math.pow(height, 2.0);
  }

}
