package com.company.signup.usecase.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.model.statistic.BirthYearUsersTotal;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.statisctic.GetBirthYearAvgBmiByYearRepository;
import com.company.signup.domain.repository.statisctic.GetBirthYearUsersTotalByYearRepository;
import com.company.signup.domain.repository.statisctic.UpdateBirthYearAvgBmiRepository;
import com.company.signup.domain.repository.user.GetUserByIdRepository;
import com.company.signup.domain.repository.user.UpdateUserRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UpdateUserTest {

  @Mock
  private GetUserByIdRepository getUserByIdRepository;

  @Mock
  private UpdateUserRepository updateUserRepository;

  @Mock
  private GetBirthYearAvgBmiByYearRepository getBirthYearAvgBmiByYearRepository;

  @Mock
  private GetBirthYearUsersTotalByYearRepository getBirthYearUsersTotalByYearRepository;

  @Mock
  private UpdateBirthYearAvgBmiRepository updateBirthYearAvgBmiRepository;

  @InjectMocks
  private UpdateUser updateUser;

  @Test
  public void given_user_then_update_save_birth_year_avg_bmi() {
    var year = LocalDate.now().getYear();

    var user = User.create(1L, "pepelucho", "123456", LocalDate.now(), 1.88F, 78F);

    when(getUserByIdRepository.execute(1L)).thenReturn(Optional.of(user));
    when(updateUserRepository.execute(user)).thenReturn(user);
    when(getBirthYearAvgBmiByYearRepository.execute(year)).thenReturn(
        Optional.of(BirthYearAvgBmi.create(year, 72.34)));
    when(getBirthYearUsersTotalByYearRepository.execute(year)).thenReturn(
        Optional.of(BirthYearUsersTotal.create(year, 1)));

    updateUser.execute(user);

    verify(updateBirthYearAvgBmiRepository, times(1)).execute(any());
  }

  @Test
  public void given_not_existing_user_then_not_update_save_birth_year_avg_bmi() {
    when(updateUserRepository.execute(any())).thenReturn(null);

    updateUser.execute(User.create(1L, "pepelucho", "123456", LocalDate.now(), 1.88F, 78F));

    verifyNoInteractions(updateBirthYearAvgBmiRepository);
  }

}
