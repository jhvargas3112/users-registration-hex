package com.company.signup.domain.usecase.statistic;

import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.statistic.BirthYearAvgBmi;

@FunctionalInterface
public interface GetPaginatedAndOrderedBirthYearsAvgBmisUseCase {

  ResultsPage<BirthYearAvgBmi> execute(Integer pageNumber, Integer sizePage, String sort);

}
