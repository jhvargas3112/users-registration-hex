package com.company.signup.infrastructure.repository.db.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.repository.user.GetUserByIdRepository;
import com.company.signup.infrastructure.client.jpa.UserJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapper;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapperImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetUserByIdDBRepositoryTest {

  private RepositoryUserMapper repositoryUserMapper;

  private UserJpaRepository userJpaRepository;

  private GetUserByIdRepository getUserByIdRepository;

  @BeforeEach
  public void setUp() {
    this.repositoryUserMapper = mock(RepositoryUserMapper.class);
    this.userJpaRepository = mock(UserJpaRepository.class);
    this.getUserByIdRepository = new GetUserByIdDBRepository(userJpaRepository,
        repositoryUserMapper);
  }

  @Test
  public void given_user_id_then_return_user() {
    var userId = 1L;

    var entityUser = new com.company.signup.infrastructure.repository.db.entity.User(any(),
        "pepelucho", "123456", LocalDate.now(), 1.88, 78.0);

    var user = new RepositoryUserMapperImpl().to(entityUser);
    when(userJpaRepository.findById(userId)).thenReturn(Optional.of(entityUser));
    when(repositoryUserMapper.to(entityUser)).thenReturn(user);

    var foundUser = getUserByIdRepository.execute(userId);

    assert foundUser.map(f -> f.equals(user)).orElse(false);

    verify(userJpaRepository, times(1)).findById(userId);
    verify(repositoryUserMapper, times(1)).to(entityUser);
  }

}
