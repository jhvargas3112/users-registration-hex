package com.company.signup.infrastructure.repository.db.mapper;

import com.company.signup.infrastructure.repository.db.entity.BirthYearUsersTotal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BirthYearUsersTotalMapper {

  BirthYearUsersTotal to(
      com.company.signup.domain.model.statistic.BirthYearUsersTotal birthYearUsersTotal);

  com.company.signup.domain.model.statistic.BirthYearUsersTotal to(
      BirthYearUsersTotal birthYearUsersTotal);

}
