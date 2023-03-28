package com.company.signup.usecase.user;

import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.GetPaginatedAndOrderedUsersRepository;
import com.company.signup.domain.usecase.user.GetPaginatedAndOrderedUsersUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetPaginatedAndOrderedUsers implements GetPaginatedAndOrderedUsersUseCase {

  private final GetPaginatedAndOrderedUsersRepository getPaginatedAndOrderedUsersRepository;

  public GetPaginatedAndOrderedUsers(
      final GetPaginatedAndOrderedUsersRepository getPaginatedAndOrderedUsersRepository) {
    this.getPaginatedAndOrderedUsersRepository = getPaginatedAndOrderedUsersRepository;
  }

  @Override
  public ResultsPage<User> execute(Integer pageNumber, Integer sizePage, String sort) {
    return getPaginatedAndOrderedUsersRepository.execute(pageNumber, sizePage, sort);
  }

}
