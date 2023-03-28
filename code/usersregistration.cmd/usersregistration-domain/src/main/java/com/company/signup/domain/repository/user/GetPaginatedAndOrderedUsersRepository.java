package com.company.signup.domain.repository.user;

import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.user.User;

@FunctionalInterface
public interface GetPaginatedAndOrderedUsersRepository {

  ResultsPage<User> execute(Integer pageNumber, Integer sizePage, String sort);

}
