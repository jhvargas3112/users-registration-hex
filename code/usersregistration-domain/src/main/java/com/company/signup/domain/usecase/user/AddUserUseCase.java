package com.company.signup.domain.usecase.user;

import com.company.signup.domain.model.user.User;

@FunctionalInterface
public interface AddUserUseCase {

  User execute(User user);

}
