package com.company.signup.api.controller.user;

import com.company.signup.api.dto.ResultsPageDTO;
import com.company.signup.api.dto.UserDTO;
import com.company.signup.api.mapper.UserResultsPageMapper;
import com.company.signup.domain.usecase.user.GetPaginatedAndOrderedUsersUseCase;
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
@RequestMapping("/users")
@Api(tags = "User API Rest")
public class UserController {

  private final GetPaginatedAndOrderedUsersUseCase getPaginatedAndOrderedUsersUseCase;

  private final UserResultsPageMapper userResultsPageMapper;

  @GetMapping
  @ApiOperation(notes = "Retrieves users in paginated mode, ordered by the given field", value = "Retrieves users in paginated mode, ordered by the given field")
  @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Ok if the users page was successfully retrieved"))
  public ResponseEntity<ResultsPageDTO<UserDTO>> getUsers(
      @ApiParam(example = "1", value = "Page number") @RequestParam Integer page,
      @ApiParam(example = "5", value = "Number of results per page") @RequestParam Integer size,
      @ApiParam(example = "birthDate", value = "Order results by this field") @RequestParam String sort) {

    return ResponseEntity.ok(userResultsPageMapper.to(
        getPaginatedAndOrderedUsersUseCase.execute(page, size, sort)));
  }

}
