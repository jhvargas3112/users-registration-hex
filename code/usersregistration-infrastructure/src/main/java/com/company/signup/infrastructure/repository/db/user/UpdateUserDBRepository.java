package com.company.signup.infrastructure.repository.db.user;

import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.UpdateUserRepository;
import com.company.signup.infrastructure.client.jpa.UserJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserDBRepository implements UpdateUserRepository {

  private final UserJpaRepository userJpaRepository;

  private final RepositoryUserMapper repositoryUserMapper;

  public UpdateUserDBRepository(final UserJpaRepository userJpaRepository,
      final RepositoryUserMapper repositoryUserMapper) {
    this.userJpaRepository = userJpaRepository;
    this.repositoryUserMapper = repositoryUserMapper;
  }


  @Override
  public User execute(User user) {
    var userToUpdate = userJpaRepository.findById(user.getId());

    return userToUpdate.map((u -> {
      u.setHeight(user.getHeight());
      u.setWeight(user.getWeight());
      
      userJpaRepository.save(u);

      return repositoryUserMapper.to(u);
    })).orElse(null);
  }

}
