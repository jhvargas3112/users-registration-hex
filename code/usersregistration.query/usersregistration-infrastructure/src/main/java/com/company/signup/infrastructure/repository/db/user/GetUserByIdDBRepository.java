package com.company.signup.infrastructure.repository.db.user;

import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.GetUserByIdRepository;
import com.company.signup.infrastructure.client.jpa.UserJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdDBRepository implements GetUserByIdRepository {

  private final UserJpaRepository userJpaRepository;

  private final RepositoryUserMapper repositoryUserMapper;

  public GetUserByIdDBRepository(final UserJpaRepository userJpaRepository,
      final RepositoryUserMapper repositoryUserMapper) {
    this.userJpaRepository = userJpaRepository;
    this.repositoryUserMapper = repositoryUserMapper;
  }

  @Override
  public Optional<User> execute(String id) {
    return userJpaRepository.findById(id).map(repositoryUserMapper::to);
  }

}
