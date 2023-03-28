package com.company.signup.api.controller.user;

import com.company.signup.api.dto.ResultsPageDTO;
import com.company.signup.api.dto.UserDTO;
import com.company.signup.api.mapper.UserMapper;
import com.company.signup.api.mapper.UserResultsPageMapper;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.usecase.user.AddUserUseCase;
import com.company.signup.domain.usecase.user.GetPaginatedAndOrderedUsersUseCase;
import com.company.signup.domain.usecase.user.GetUserByIdUseCase;
import com.company.signup.domain.usecase.user.UpdateUserUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.net.URI;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Api(tags = "User API Rest")
public class UserController {

  private final AddUserUseCase addUserUseCase;

  private final UpdateUserUseCase updateUserUseCase;

  private final GetUserByIdUseCase getUserByIdUseCase;

  private final GetPaginatedAndOrderedUsersUseCase getPaginatedAndOrderedUsersUseCase;

  private final UserMapper userMapper;

  private final UserResultsPageMapper userResultsPageMapper;

  @PostMapping
  @ApiOperation(notes = "Creates a new user", value = "creates a new user")
  @ApiResponses(value = @ApiResponse(responseCode = "201", description = "If the create user operation was successful"))
  public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
    User user = addUserUseCase.execute(userMapper.to(userDTO));

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userName}")
        .buildAndExpand(user.getUserName()).toUri();

    return ResponseEntity.created(location).build();
  }

  @PutMapping
  @ApiOperation(notes = "Updates the given user", value = "Updates the given user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "If the update user operation was successful"),
      @ApiResponse(responseCode = "404", description = "If the user to update does not exists")})
  public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
    return getUserByIdUseCase.execute(userDTO.getId()).isEmpty() ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(userMapper.to(updateUserUseCase.execute(userMapper.to(userDTO))));
  }

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
