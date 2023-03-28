package com.company.signup.infrastructure.repository.db.statistic;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.repository.statisctic.SaveBirthYearAvgBmiRepository;
import com.company.signup.infrastructure.client.jpa.BirthYearAvgBmiJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapper;
import org.springframework.stereotype.Service;

@Service
public class SaveBirthYearAvgBmiDBRepository implements SaveBirthYearAvgBmiRepository {

  private final BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository;

  private final RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper;

  public SaveBirthYearAvgBmiDBRepository(BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository,
      RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper) {
    this.birthYearAvgBmiJpaRepository = birthYearAvgBmiJpaRepository;
    this.repositoryBirthYearAvgBmiMapper = repositoryBirthYearAvgBmiMapper;
  }

  @Override
  public BirthYearAvgBmi execute(BirthYearAvgBmi birthYearAvgBmi) {
    var savedBirthYearAvgBmi = birthYearAvgBmiJpaRepository.save(repositoryBirthYearAvgBmiMapper.to(birthYearAvgBmi));
    return repositoryBirthYearAvgBmiMapper.to(savedBirthYearAvgBmi);
  }

}
