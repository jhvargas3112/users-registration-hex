package com.company.signup.domain.repository.user;

import com.company.signup.domain.model.user.User;

@FunctionalInterface
public interface UpdateUserRepository {

  User execute(User user);

}
