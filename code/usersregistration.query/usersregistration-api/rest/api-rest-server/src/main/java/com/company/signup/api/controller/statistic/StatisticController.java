package com.company.signup.api.controller.statistic;

import com.company.signup.api.dto.BirthYearAvgBmiDTO;
import com.company.signup.api.dto.ResultsPageDTO;
import com.company.signup.api.mapper.BirthYearAvgBmiResultsPageMapper;
import com.company.signup.domain.usecase.statistic.GetPaginatedAndOrderedBirthYearsAvgBmisUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users/statistics")
@Api(tags = "Statistics API Rest")
public class StatisticController {

  private final GetPaginatedAndOrderedBirthYearsAvgBmisUseCase getPaginatedAndOrderedBirthYearsAvgBmisUseCase;

  private final BirthYearAvgBmiResultsPageMapper birthYearAvgBmiResultsPageMapper;

  @GetMapping("/birth-year-avg-bmi")
  @ApiOperation(notes = "Retrieves Birth Years Avg BMIs in paginated mode, ordered by the given field", value = "Retrieves Birth Years Avg BMIs in paginated mode, ordered by the given field")
  @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Ok if the Birth Years Avg BMIs page was successfully retrieved"))
  public ResponseEntity<ResultsPageDTO<BirthYearAvgBmiDTO>> getBirthYearsAvgBmis(
      @ApiParam(example = "1", value = "Page number") @RequestParam Integer page,
      @ApiParam(example = "5", value = "Number of results per page") @RequestParam Integer size,
      @ApiParam(example = "birthDate", value = "Order results by this field") @RequestParam String sort) {

    return ResponseEntity.ok(birthYearAvgBmiResultsPageMapper.to(getPaginatedAndOrderedBirthYearsAvgBmisUseCase.execute(page, size, sort)));
  }

}
