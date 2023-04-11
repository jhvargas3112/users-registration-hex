package com.company.signup.domain.usecase.user;

import com.company.signup.domain.model.user.User;
import java.util.Optional;

@FunctionalInterface
public interface GetUserByIdUseCase {

  Optional<User> execute(String id);

}
