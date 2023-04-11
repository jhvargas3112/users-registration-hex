package com.company.signup.usecase.user;

import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.AddUserRepository;
import com.company.signup.domain.usecase.user.AddUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class AddUser implements AddUserUseCase {

  private final AddUserRepository addUserRepository;

  public AddUser(AddUserRepository addUserRepository) {
    this.addUserRepository = addUserRepository;
  }

  @Override
  public User execute(User user) {
    return addUserRepository.execute(user);
  }

}
