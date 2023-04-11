package com.company.signup.infrastructure.repository.db.user;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.model.user.BirthDate;
import com.company.signup.domain.model.user.BodyMeasurements;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.UpdateUserRepository;
import com.company.signup.infrastructure.client.jpa.UserJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapper;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdateUserDBRepositoryTest {

  private RepositoryUserMapper repositoryUserMapper;

  private UserJpaRepository userJpaRepository;

  private UpdateUserRepository updateUserRepository;

  @BeforeEach
  public void setUp() {
    this.repositoryUserMapper = mock(RepositoryUserMapper.class);
    this.userJpaRepository = mock(UserJpaRepository.class);
    this.updateUserRepository = new UpdateUserDBRepository(userJpaRepository, repositoryUserMapper);
  }

  @Test
  public void given_existing_user_then_user_updated() {
    var id = UUID.randomUUID().toString();

    var updatedUser = User.create(id, "pepelucho", "123456", BirthDate.create(LocalDate.now()),
        BodyMeasurements.create(2.22, 95.2));
    var userToUpdate = new com.company.signup.infrastructure.repository.db.entity.User(id,
        "pepelucho", "123456", LocalDate.now(), 2.22, 95.2);

    when(userJpaRepository.findById(id)).thenReturn(Optional.of(userToUpdate));
    when(userJpaRepository.save(userToUpdate)).thenReturn(userToUpdate);
    when(repositoryUserMapper.to(userToUpdate)).thenReturn(updatedUser);

    updateUserRepository.execute(updatedUser);

    verify(userJpaRepository, times(1)).findById(id);
    verify(userJpaRepository, times(1)).save(userToUpdate);
    verify(repositoryUserMapper, times(1)).to(userToUpdate);
  }

  @Test
  public void given_not_existing_user_then_user_not_updated() {
    var id = UUID.randomUUID().toString();

    when(userJpaRepository.findById(id)).thenReturn(Optional.empty());

    assertNull(updateUserRepository.execute(
        User.create(id, "pepelucho", "123456", BirthDate.create(LocalDate.now()),
            BodyMeasurements.create(2.22, 95.2))));

    verify(userJpaRepository, times(1)).findById(id);
  }

}
