package com.company.signup.api.mapper;

import com.company.signup.api.dto.BirthYearAvgBmiDTO;
import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BirthYearAvgBmiMapper {

  BirthYearAvgBmi to(BirthYearAvgBmiDTO birthYearAvgBmiDTO);

  BirthYearAvgBmiDTO to(BirthYearAvgBmi birthYearAvgBmi);

  List<BirthYearAvgBmiDTO> to(List<BirthYearAvgBmi> BirthYearsAvgBmis);

}
