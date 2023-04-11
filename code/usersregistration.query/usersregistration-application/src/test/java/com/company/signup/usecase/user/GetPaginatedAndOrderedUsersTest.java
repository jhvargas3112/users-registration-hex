package com.company.signup.usecase.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.user.BirthDate;
import com.company.signup.domain.model.user.BodyMeasurements;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.GetPaginatedAndOrderedUsersRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
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
    var id1 = UUID.randomUUID().toString();
    var id2 = UUID.randomUUID().toString();
    
    var user1 = User.create(id1, "pepelucho1", "123456", BirthDate.create(LocalDate.now()),
        BodyMeasurements.create(1.88, 78.0));
    var user2 = User.create(id2, "pepelucho2", "12345678", BirthDate.create(LocalDate.now()),
        BodyMeasurements.create(1.90, 87.0));

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
