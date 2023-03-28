package com.company.signup.infrastructure.repository.db.statistic;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.repository.statisctic.GetPaginatedAndOrderedBirthYearAvgBmiRepository;
import com.company.signup.infrastructure.client.jpa.BirthYearAvgBmiJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GetPaginatedAndOrderedBirthYearAvgBmiDBRepository implements
    GetPaginatedAndOrderedBirthYearAvgBmiRepository {

  private final BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository;

  private final RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper;

  public GetPaginatedAndOrderedBirthYearAvgBmiDBRepository(
      BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository,
      RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper) {
    this.birthYearAvgBmiJpaRepository = birthYearAvgBmiJpaRepository;
    this.repositoryBirthYearAvgBmiMapper = repositoryBirthYearAvgBmiMapper;
  }

  @Override
  public ResultsPage<BirthYearAvgBmi> execute(Integer pageNumber, Integer sizePage, String sort) {
    Page<com.company.signup.infrastructure.repository.db.entity.BirthYearAvgBmi> page = birthYearAvgBmiJpaRepository.findAll(
        PageRequest.of(pageNumber, sizePage, Sort.by(sort)));

    var BirthYearsAvgBmis = page.stream().parallel().map(repositoryBirthYearAvgBmiMapper::to).toList();

    return new ResultsPage<>(BirthYearsAvgBmis, page.getTotalElements(), page.getSize(), pageNumber,
        page.getTotalPages());
  }
}
