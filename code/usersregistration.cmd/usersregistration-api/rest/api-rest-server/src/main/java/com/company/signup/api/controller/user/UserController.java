package com.company.signup.api.controller.user;

import com.company.signup.domain.command.AddUserCommand;
import com.company.signup.domain.usecase.AddUserUseCase;
import com.company.signup.domain.usecase.UpdateUserUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Api(tags = "User API Rest")
public class UserController {

  private final AddUserUseCase addUserUseCase;

  private final UpdateUserUseCase updateUserUseCase;

  @PostMapping
  @ApiOperation(notes = "Creates a new user", value = "creates a new user")
  @ApiResponses(value = @ApiResponse(responseCode = "201", description = "If the create user operation was successful"))
  public ResponseEntity<String> createUser(@Valid @RequestBody AddUserCommand addUserCommand) {
    addUserCommand.setId(UUID.randomUUID().toString());

    addUserUseCase.execute(addUserCommand);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userName}")
        .buildAndExpand(addUserCommand.getUserName()).toUri();

    return ResponseEntity.created(location).build();
  }

  /* @PutMapping
  @ApiOperation(notes = "Updates the given user", value = "Updates the given user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "If the update user operation was successful"),
      @ApiResponse(responseCode = "404", description = "If the user to update does not exists")})
  public ResponseEntity<UpdateUserCommand> updateUser(
      @Valid @RequestBody UpdateUserCommand updateUserCommand) {
    return ResponseEntity.ok(updateUserUseCase.execute(updateUserCommand)));
  } */

}
