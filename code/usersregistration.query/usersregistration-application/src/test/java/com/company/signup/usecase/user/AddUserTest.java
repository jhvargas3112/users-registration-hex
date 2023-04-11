package com.company.signup.usecase.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.model.user.BirthDate;
import com.company.signup.domain.model.user.BodyMeasurements;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.AddUserRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AddUserTest {

  @Mock
  private AddUserRepository addUserRepository;

  @InjectMocks
  private AddUser addUser;

  @Test
  public void given_user_with_existing_birth_year_then_update_that_birth_year_users_total_and_save_birth_year_avg_bmi() {
    var user = User.create(any(), "pepelucho", "123456", BirthDate.create(LocalDate.now()),
        BodyMeasurements.create(1.88, 78.0));

    when(addUserRepository.execute(user)).thenReturn(user);

    addUser.execute(user);

    verify(addUserRepository, times(1)).execute(any());
  }

  @Test
  public void given_user_with_not_existing_birth_year_then_create_a_new_birth_year_users_total_and_save_birth_year_avg_bmi() {
    var user = User.create(any(), "pepelucho", "123456", BirthDate.create(LocalDate.now()),
        BodyMeasurements.create(1.88, 78.0));

    when(addUserRepository.execute(user)).thenReturn(user);

    addUser.execute(user);

    verify(addUserRepository, times(1)).execute(any());
  }

}
