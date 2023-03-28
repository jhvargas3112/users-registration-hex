package com.company.signup.api.controller.statistic;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.company.signup.api.dto.BirthYearAvgBmiDTO;
import com.company.signup.api.dto.ResultsPageDTO;
import com.company.signup.api.mapper.BirthYearAvgBmiResultsPageMapper;
import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.usecase.statistic.GetPaginatedAndOrderedBirthYearsAvgBmisUseCase;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(StatisticController.class)
public class StatisticControllerTest {

  @MockBean
  private GetPaginatedAndOrderedBirthYearsAvgBmisUseCase getPaginatedAndOrderedBirthYearsAvgBmisUseCase;

  @MockBean
  private BirthYearAvgBmiResultsPageMapper birthYearAvgBmiResultsPageMapper;

  @Autowired
  private MockMvc mvc;

  @BeforeEach
  public void setUpBefore() {
    List<BirthYearAvgBmi> birthYearsAvgBmis = new ArrayList<>();
    birthYearsAvgBmis.add(new BirthYearAvgBmi(1990, 25.23));
    birthYearsAvgBmis.add(new BirthYearAvgBmi(1988, 28.91));
    ResultsPage<BirthYearAvgBmi> resultsPage = new ResultsPage<>(birthYearsAvgBmis, 2L, 5, 0, 1);

    List<BirthYearAvgBmiDTO> birthYearsAvgBmisDTOs = new ArrayList<>();
    birthYearsAvgBmisDTOs.add(new BirthYearAvgBmiDTO(1990, 25.23));
    birthYearsAvgBmisDTOs.add(new BirthYearAvgBmiDTO(1988, 28.91));
    ResultsPageDTO<BirthYearAvgBmiDTO> resultsPageDTO = new ResultsPageDTO<>(birthYearsAvgBmisDTOs,
        2L, 5, 0, 1);

    when(getPaginatedAndOrderedBirthYearsAvgBmisUseCase.execute(0, 5, "avgBmi")).thenReturn(
        resultsPage);
    when(birthYearAvgBmiResultsPageMapper.to(resultsPage)).thenReturn(resultsPageDTO);
  }

  @Test
  public void given_get_birth_years_avg_bmis_call_then_return_json_object_with_birth_years_avg_bmis_results_page()
      throws Exception {

    mvc.perform(get("/users/statistics/birth-year-avg-bmi?page=0&size=5&sort=avgBmi")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isMap())
        .andExpect(jsonPath("$.elements").isArray())
        .andExpect(jsonPath("$.elements", hasSize(2)))
        .andExpect(jsonPath("$.elements[0].year", is(1990)))
        .andExpect(jsonPath("$.elements[0].avgBmi", is(25.23)))
        .andExpect(jsonPath("$.elements[1].year", is(1988)))
        .andExpect(jsonPath("$.elements[1].avgBmi", is(28.91)))
        .andExpect(jsonPath("$.totalResults", is(2)))
        .andExpect(jsonPath("$.size", is(5)))
        .andExpect(jsonPath("$.currentPage", is(0)))
        .andExpect(jsonPath("$.totalPages", is(1)));
  }

}
