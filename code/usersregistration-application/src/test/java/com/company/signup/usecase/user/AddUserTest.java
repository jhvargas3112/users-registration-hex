package com.company.signup.usecase.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.model.statistic.BirthYearUsersTotal;
import com.company.signup.domain.model.user.BirthDate;
import com.company.signup.domain.model.user.BodyMeasurements;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.statisctic.GetBirthYearAvgBmiByYearRepository;
import com.company.signup.domain.repository.statisctic.GetBirthYearUsersTotalByYearRepository;
import com.company.signup.domain.repository.statisctic.SaveBirthYearAvgBmiRepository;
import com.company.signup.domain.repository.statisctic.SaveBirthYearUsersTotalRepository;
import com.company.signup.domain.repository.statisctic.UpdateBirthYearUsersTotalRepository;
import com.company.signup.domain.repository.user.AddUserRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AddUserTest {

  @Mock
  private AddUserRepository addUserRepository;

  @Mock
  private GetBirthYearAvgBmiByYearRepository getBirthYearAvgBmiByYearRepository;

  @Mock
  private GetBirthYearUsersTotalByYearRepository getBirthYearUsersTotalByYearRepository;

  @Mock
  private SaveBirthYearUsersTotalRepository saveBirthYearUsersTotalRepository;

  @Mock
  private UpdateBirthYearUsersTotalRepository updateBirthYearUsersTotalRepository;

  @Mock
  private SaveBirthYearAvgBmiRepository saveBirthYearAvgBmiRepository;

  @InjectMocks
  private AddUser addUser;

  @Test
  public void given_user_with_existing_birth_year_then_update_that_birth_year_users_total_and_save_birth_year_avg_bmi() {
    var user = User.create(any(), "pepelucho", "123456", BirthDate.create(LocalDate.now()),
        BodyMeasurements.create(1.88, 78.0));

    when(getBirthYearUsersTotalByYearRepository.execute(1988)).thenReturn(
        Optional.of(BirthYearUsersTotal.create(1988, 1)));
    when(addUserRepository.execute(user)).thenReturn(user);

    addUser.execute(user);

    verify(updateBirthYearUsersTotalRepository, times(1)).execute(any());
    verify(saveBirthYearAvgBmiRepository, times(1)).execute(any());
  }

  @Test
  public void given_user_with_not_existing_birth_year_then_create_a_new_birth_year_users_total_and_save_birth_year_avg_bmi() {
    var user = User.create(any(), "pepelucho", "123456", BirthDate.create(LocalDate.now()),
        BodyMeasurements.create(1.88, 78.0));

    when(getBirthYearUsersTotalByYearRepository.execute(1998)).thenReturn(Optional.empty());
    when(addUserRepository.execute(user)).thenReturn(user);

    addUser.execute(user);

    verify(saveBirthYearUsersTotalRepository, times(1)).execute(any());
    verify(saveBirthYearAvgBmiRepository, times(1)).execute(any());
  }

}
