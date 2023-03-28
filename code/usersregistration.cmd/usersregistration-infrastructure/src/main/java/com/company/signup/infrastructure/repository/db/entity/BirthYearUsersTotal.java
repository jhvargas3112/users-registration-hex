package com.company.signup.infrastructure.repository.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BirthYearUsersTotal {

  @Id
  @Column(name = "BIRTH_YEAR")
  private Integer year;

  private Integer total;

}
