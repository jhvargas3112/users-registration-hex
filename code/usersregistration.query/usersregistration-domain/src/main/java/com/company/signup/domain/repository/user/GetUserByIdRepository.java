package com.company.signup.domain.repository.user;

import com.company.signup.domain.model.user.User;
import java.util.Optional;

@FunctionalInterface
public interface GetUserByIdRepository {

  Optional<User> execute(String id);

}
