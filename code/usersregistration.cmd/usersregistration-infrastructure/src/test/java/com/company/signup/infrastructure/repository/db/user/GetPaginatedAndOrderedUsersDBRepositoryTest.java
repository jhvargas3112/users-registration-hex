package com.company.signup.infrastructure.repository.db.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.infrastructure.client.jpa.UserJpaRepository;
import com.company.signup.infrastructure.repository.db.entity.User;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapper;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryUserMapperImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class GetPaginatedAndOrderedUsersDBRepositoryTest {

  private GetPaginatedAndOrderedUsersDBRepository getPaginatedAndOrderedUsersDBRepository;

  private UserJpaRepository userJpaRepository;

  private RepositoryUserMapper repositoryUserMapper;

  @BeforeEach
  public void setUp() {
    this.userJpaRepository = mock(UserJpaRepository.class);
    this.repositoryUserMapper = mock(RepositoryUserMapper.class);
    getPaginatedAndOrderedUsersDBRepository = new GetPaginatedAndOrderedUsersDBRepository(
        userJpaRepository, repositoryUserMapper);
  }

  @Test
  public void given_size_and_page_and_sortby_params_then_return_paginated_users() {
    var user = new User(1L, "pepelucho1", "123456", LocalDate.now(), 1.88, 78.0);

    var sortBy = "birthDate";

    var userTest = new RepositoryUserMapperImpl().to(user);

    List<com.company.signup.infrastructure.repository.db.entity.User> usersTest = new ArrayList<>();
    usersTest.add(user);

    Page<User> resultsPage = new PageImpl<>(usersTest);

    when(userJpaRepository.findAll(PageRequest.of(0, 1, Sort.by(sortBy)))).thenReturn(resultsPage);
    when(repositoryUserMapper.to(user)).thenReturn(userTest);

    getPaginatedAndOrderedUsersDBRepository.execute(0, 1, sortBy);

    verify(repositoryUserMapper, times(1)).to(user);

  }

}
