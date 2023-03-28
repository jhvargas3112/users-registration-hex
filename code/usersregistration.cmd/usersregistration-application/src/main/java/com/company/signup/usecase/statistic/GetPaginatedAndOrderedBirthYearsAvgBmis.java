package com.company.signup.usecase.statistic;

import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.repository.statisctic.GetPaginatedAndOrderedBirthYearAvgBmiRepository;
import com.company.signup.domain.usecase.statistic.GetPaginatedAndOrderedBirthYearsAvgBmisUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetPaginatedAndOrderedBirthYearsAvgBmis implements
    GetPaginatedAndOrderedBirthYearsAvgBmisUseCase {

  private final GetPaginatedAndOrderedBirthYearAvgBmiRepository getPaginatedAndOrderedBirthYearAvgBmiRepository;

  public GetPaginatedAndOrderedBirthYearsAvgBmis(
      GetPaginatedAndOrderedBirthYearAvgBmiRepository getPaginatedAndOrderedBirthYearAvgBmiRepository) {
    this.getPaginatedAndOrderedBirthYearAvgBmiRepository = getPaginatedAndOrderedBirthYearAvgBmiRepository;
  }

  @Override
  public ResultsPage<BirthYearAvgBmi> execute(Integer pageNumber, Integer sizePage, String sort) {
    return getPaginatedAndOrderedBirthYearAvgBmiRepository.execute(pageNumber, sizePage, sort);
  }

}
