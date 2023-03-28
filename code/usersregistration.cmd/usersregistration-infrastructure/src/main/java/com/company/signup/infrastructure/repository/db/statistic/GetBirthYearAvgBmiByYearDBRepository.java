package com.company.signup.infrastructure.repository.db.statistic;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.repository.statisctic.GetBirthYearAvgBmiByYearRepository;
import com.company.signup.infrastructure.client.jpa.BirthYearAvgBmiJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GetBirthYearAvgBmiByYearDBRepository implements GetBirthYearAvgBmiByYearRepository {

  private final BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository;

  private final RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper;

  public GetBirthYearAvgBmiByYearDBRepository(
      BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository,
      RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper) {
    this.birthYearAvgBmiJpaRepository = birthYearAvgBmiJpaRepository;
    this.repositoryBirthYearAvgBmiMapper = repositoryBirthYearAvgBmiMapper;
  }

  @Override
  public Optional<BirthYearAvgBmi> execute(Integer year) {
    return birthYearAvgBmiJpaRepository.findByYear(year).map(repositoryBirthYearAvgBmiMapper::to);
  }

}
