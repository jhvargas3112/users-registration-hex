package com.company.signup.usecase.user;

import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.GetUserByIdRepository;
import com.company.signup.domain.usecase.user.GetUserByIdUseCase;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GetUserById implements GetUserByIdUseCase {

  private final GetUserByIdRepository getUserByIdRepository;

  public GetUserById(final GetUserByIdRepository getUserByIdRepository) {
    this.getUserByIdRepository = getUserByIdRepository;
  }

  @Override
  public Optional<User> execute(Long id) {
    return getUserByIdRepository.execute(id);
  }

}
