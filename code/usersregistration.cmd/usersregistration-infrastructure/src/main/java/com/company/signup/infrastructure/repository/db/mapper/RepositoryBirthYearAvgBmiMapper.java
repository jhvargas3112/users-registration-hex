package com.company.signup.infrastructure.repository.db.mapper;

import com.company.signup.infrastructure.repository.db.entity.BirthYearAvgBmi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepositoryBirthYearAvgBmiMapper {

  BirthYearAvgBmi to(
      com.company.signup.domain.model.statistic.BirthYearAvgBmi birthYearUsersTotal);

  com.company.signup.domain.model.statistic.BirthYearAvgBmi to(
      BirthYearAvgBmi birthYearUsersTotal);

}
