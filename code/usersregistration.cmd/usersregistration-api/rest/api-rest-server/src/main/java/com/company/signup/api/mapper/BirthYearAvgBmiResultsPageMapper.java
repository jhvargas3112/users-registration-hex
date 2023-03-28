package com.company.signup.api.mapper;

import com.company.signup.api.dto.BirthYearAvgBmiDTO;
import com.company.signup.api.dto.ResultsPageDTO;
import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BirthYearAvgBmiMapper.class)
public interface BirthYearAvgBmiResultsPageMapper {

  ResultsPage<BirthYearAvgBmi> to(ResultsPageDTO<BirthYearAvgBmiDTO> resultsPageDTO);

  ResultsPageDTO<BirthYearAvgBmiDTO> to(ResultsPage<BirthYearAvgBmi> resultsPage);

}
