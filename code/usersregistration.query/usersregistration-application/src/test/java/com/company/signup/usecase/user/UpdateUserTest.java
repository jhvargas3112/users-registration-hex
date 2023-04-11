package com.company.signup.usecase.user;

import static org.mockito.Mockito.when;

import com.company.signup.domain.model.user.BirthDate;
import com.company.signup.domain.model.user.BodyMeasurements;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.GetUserByIdRepository;
import com.company.signup.domain.repository.user.UpdateUserRepository;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateUserTest {

  @Mock
  private GetUserByIdRepository getUserByIdRepository;

  @Mock
  private UpdateUserRepository updateUserRepository;

  @InjectMocks
  private UpdateUser updateUser;

  @Test
  public void given_user_then_update_save_birth_year_avg_bmi() {
    var year = LocalDate.now().getYear();

    var id = UUID.randomUUID().toString();

    var user = User.create(id, "pepelucho", "123456", BirthDate.create(LocalDate.now()),
        BodyMeasurements.create(1.88, 78.0));

    when(updateUserRepository.execute(user)).thenReturn(user);

    updateUser.execute(user);
  }

}
