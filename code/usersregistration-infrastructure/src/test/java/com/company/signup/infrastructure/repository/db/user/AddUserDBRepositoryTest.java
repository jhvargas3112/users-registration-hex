package com.company.signup.infrastructure.repository.db.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.AddUserRepository;
import com.company.signup.infrastructure.client.jpa.UserJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapper;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapperImpl;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddUserDBRepositoryTest {

  private RepositoryUserMapper repositoryUserMapper;

  private UserJpaRepository userJpaRepository;

  private AddUserRepository addUserRepository;

  @BeforeEach
  public void setUp() {
    this.repositoryUserMapper = mock(RepositoryUserMapper.class);
    this.userJpaRepository = mock(UserJpaRepository.class);
    this.addUserRepository = new AddUserDBRepository(userJpaRepository, repositoryUserMapper);
  }

  @Test
  public void given_user_then_user_saved() {
    var user = User.create(any(), "pepelucho", "123456", LocalDate.now(), 1.88F, 78F);
    var entityUser = new RepositoryUserMapperImpl().to(user);

    when(repositoryUserMapper.to(user)).thenReturn(entityUser);
    when(userJpaRepository.save(entityUser)).thenReturn(entityUser);
    when(repositoryUserMapper.to(entityUser)).thenReturn(user);

    addUserRepository.execute(user);

    verify(repositoryUserMapper, times(1)).to(user);
    verify(userJpaRepository, times(1)).save(entityUser);
    verify(repositoryUserMapper, times(1)).to(entityUser);
  }

}
