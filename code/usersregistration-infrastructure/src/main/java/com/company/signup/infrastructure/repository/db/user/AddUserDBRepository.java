package com.company.signup.infrastructure.repository.db.user;

import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.AddUserRepository;
import com.company.signup.infrastructure.client.jpa.UserJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapper;
import org.springframework.stereotype.Service;

@Service
public class AddUserDBRepository implements AddUserRepository {

  private final UserJpaRepository userJpaRepository;

  private final RepositoryUserMapper repositoryUserMapper;

  public AddUserDBRepository(final UserJpaRepository userJpaRepository,
      final RepositoryUserMapper repositoryUserMapper) {
    this.userJpaRepository = userJpaRepository;
    this.repositoryUserMapper = repositoryUserMapper;
  }

  @Override
  public User execute(User user) {
    var savedUser = userJpaRepository.save(repositoryUserMapper.to(user));
    return repositoryUserMapper.to(savedUser);
  }

}
