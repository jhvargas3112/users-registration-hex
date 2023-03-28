package com.company.signup.usecase.statistic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.repository.statisctic.GetPaginatedAndOrderedBirthYearAvgBmiRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GetPaginatedAndOrderedBirthYearsAvgBmisTest {

  private GetPaginatedAndOrderedBirthYearAvgBmiRepository getPaginatedAndOrderedBirthYearAvgBmiRepository;

  private GetPaginatedAndOrderedBirthYearsAvgBmis getPaginatedAndOrderedBirthYearsAvgBmis;

  @BeforeEach
  void setUp() {
    this.getPaginatedAndOrderedBirthYearAvgBmiRepository = mock(
        GetPaginatedAndOrderedBirthYearAvgBmiRepository.class);
    this.getPaginatedAndOrderedBirthYearsAvgBmis = new GetPaginatedAndOrderedBirthYearsAvgBmis(
        getPaginatedAndOrderedBirthYearAvgBmiRepository);
  }

  @Test
  public void given_size_and_page_then_return_birth_year_avg_bmi_page() {
    var birtYearAvgBmi1 = BirthYearAvgBmi.create(1988, 72.54);
    var birtYearAvgBmi2 = BirthYearAvgBmi.create(2000, 87.32);

    var sortBy = "avgBmi";

    var pageResult = new ResultsPage<>(List.of(birtYearAvgBmi1, birtYearAvgBmi2), 2L, 2, 1, 1);

    when(getPaginatedAndOrderedBirthYearAvgBmiRepository.execute(1, 2, sortBy)).thenReturn(
        pageResult);

    var page = getPaginatedAndOrderedBirthYearsAvgBmis.execute(1, 2, sortBy);

    assertEquals(2, page.getElements().size());
    assert page.getTotalResults().equals(2L);
    assert page.getSize().equals(2);
    assert page.getCurrentPage().equals(1);
    assert page.getTotalPages().equals(1);

    verify(getPaginatedAndOrderedBirthYearAvgBmiRepository, times(1)).execute(1, 2, sortBy);
  }

}
