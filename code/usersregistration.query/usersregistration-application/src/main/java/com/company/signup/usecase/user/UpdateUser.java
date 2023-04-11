package com.company.signup.usecase.user;

import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.UpdateUserRepository;
import com.company.signup.domain.usecase.user.UpdateUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser implements UpdateUserUseCase {

  private final UpdateUserRepository updateUserRepository;

  public UpdateUser(UpdateUserRepository updateUserRepository) {
    this.updateUserRepository = updateUserRepository;
  }

  @Override
  public User execute(User user) {
    return updateUserRepository.execute(user);
  }

}
