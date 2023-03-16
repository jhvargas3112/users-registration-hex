package com.company.signup.usecase.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.GetPaginatedAndOrderedUsersRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GetPaginatedAndOrderedUsersTest {

  private GetPaginatedAndOrderedUsersRepository getPaginatedAndOrderedUsersRepository;

  private GetPaginatedAndOrderedUsers getPaginatedAndOrderedUsers;

  @BeforeEach
  void setUp() {
    this.getPaginatedAndOrderedUsersRepository = mock(GetPaginatedAndOrderedUsersRepository.class);
    this.getPaginatedAndOrderedUsers = new GetPaginatedAndOrderedUsers(
        getPaginatedAndOrderedUsersRepository);
  }

  @Test
  public void given_size_and_page_and_sortby_params_then_return_users_page() {
    var user1 = User.create(1L, "pepelucho1", "123456", LocalDate.now(), 1.88F, 78F);
    var user2 = User.create(2L, "pepelucho2", "12345678", LocalDate.now(), 1.90F, 87F);

    var sortBy = "birthDate";

    var resultsPage = new ResultsPage<>(List.of(user1, user2), 2L, 2, 1, 1);

    when(getPaginatedAndOrderedUsersRepository.execute(1, 2, sortBy))
        .thenReturn(resultsPage);

    var page = getPaginatedAndOrderedUsers.execute(1, 2, sortBy);

    assert page.getElements().size() == 2;
    assert page.getTotalResults().equals(2L);
    assert page.getSize().equals(2);
    assert page.getCurrentPage().equals(1);
    assert page.getTotalPages().equals(1);

    verify(getPaginatedAndOrderedUsersRepository, times(1)).execute(1, 2, sortBy);
  }

}
