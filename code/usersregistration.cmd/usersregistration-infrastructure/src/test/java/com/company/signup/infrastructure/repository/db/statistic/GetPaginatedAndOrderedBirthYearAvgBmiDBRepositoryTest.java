package com.company.signup.infrastructure.repository.db.statistic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.infrastructure.client.jpa.BirthYearAvgBmiJpaRepository;
import com.company.signup.infrastructure.repository.db.entity.BirthYearAvgBmi;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapper;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapperImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class GetPaginatedAndOrderedBirthYearAvgBmiDBRepositoryTest {

  private BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository;

  private RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper;

  private GetPaginatedAndOrderedBirthYearAvgBmiDBRepository getPaginatedAndOrderedBirthYearAvgBmiDBRepository;

  @BeforeEach
  public void setUp() {
    this.birthYearAvgBmiJpaRepository = mock(BirthYearAvgBmiJpaRepository.class);
    this.repositoryBirthYearAvgBmiMapper = mock(RepositoryBirthYearAvgBmiMapper.class);
    getPaginatedAndOrderedBirthYearAvgBmiDBRepository = new GetPaginatedAndOrderedBirthYearAvgBmiDBRepository(
        birthYearAvgBmiJpaRepository, repositoryBirthYearAvgBmiMapper);
  }

  @Test
  public void given_size_and_page_and_sortby_params_then_return_paginated_birth_years_avg_bmis() {
    var birthYearAvgBmi = new BirthYearAvgBmi(1990, 76.24);

    var sortBy = "avgBmi";

    var birthYearAvgBmiTest = new RepositoryBirthYearAvgBmiMapperImpl().to(birthYearAvgBmi);

    List<BirthYearAvgBmi> birthYearsAvgBmisTest = new ArrayList<>();
    birthYearsAvgBmisTest.add(birthYearAvgBmi);

    Page<BirthYearAvgBmi> resultsPage = new PageImpl<>(birthYearsAvgBmisTest);

    when(birthYearAvgBmiJpaRepository.findAll(PageRequest.of(0, 1, Sort.by(sortBy)))).thenReturn(
        resultsPage);
    when(repositoryBirthYearAvgBmiMapper.to(birthYearAvgBmi)).thenReturn(birthYearAvgBmiTest);

    getPaginatedAndOrderedBirthYearAvgBmiDBRepository.execute(0, 1, sortBy);

    verify(repositoryBirthYearAvgBmiMapper, times(1)).to(birthYearAvgBmi);

  }

}
