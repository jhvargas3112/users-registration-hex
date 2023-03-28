package com.company.signup.domain.usecase.user;

import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.user.User;

public interface GetPaginatedAndOrderedUsersUseCase {

  ResultsPage<User> execute(Integer pageNumber, Integer sizePage, String sort);

}
