package com.company.signup.infrastructure.repository.db.user;

import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.repository.user.GetPaginatedAndOrderedUsersRepository;
import com.company.signup.infrastructure.client.jpa.UserJpaRepository;
import com.company.signup.infrastructure.repository.db.entity.User;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GetPaginatedAndOrderedUsersDBRepository implements
    GetPaginatedAndOrderedUsersRepository {

  private final UserJpaRepository userJpaRepository;

  private final RepositoryUserMapper repositoryUserMapper;

  public GetPaginatedAndOrderedUsersDBRepository(final UserJpaRepository userJpaRepository,
      final RepositoryUserMapper repositoryUserMapper) {
    this.userJpaRepository = userJpaRepository;
    this.repositoryUserMapper = repositoryUserMapper;
  }

  @Override
  public ResultsPage<com.company.signup.domain.model.user.User> execute(Integer pageNumber,
      Integer sizePage, String sort) {
    Page<User> page = userJpaRepository.findAll(
        PageRequest.of(pageNumber, sizePage, Sort.by(sort)));

    var users = page.stream().parallel().map(repositoryUserMapper::to).toList();

    return new ResultsPage<>(users, page.getTotalElements(), page.getSize(), pageNumber,
        page.getTotalPages());
  }

}
