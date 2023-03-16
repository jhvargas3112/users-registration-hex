package com.company.signup.infrastructure.repository.db.statistic;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.repository.statisctic.UpdateBirthYearAvgBmiRepository;
import com.company.signup.infrastructure.client.jpa.BirthYearAvgBmiJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateBirthYearAvgBmiDBRepository implements UpdateBirthYearAvgBmiRepository {

  private final BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository;

  private final RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper;

  public UpdateBirthYearAvgBmiDBRepository(
      BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository,
      RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper) {
    this.birthYearAvgBmiJpaRepository = birthYearAvgBmiJpaRepository;
    this.repositoryBirthYearAvgBmiMapper = repositoryBirthYearAvgBmiMapper;
  }

  @Override
  public BirthYearAvgBmi execute(BirthYearAvgBmi birthYearAvgBmi) {
    var BirthYearAvgBmiToUpdate = birthYearAvgBmiJpaRepository.findByYear(
        birthYearAvgBmi.getYear());

    return BirthYearAvgBmiToUpdate.map((b -> {
      b.setAvgBmi(birthYearAvgBmi.getAvgBmi());
      birthYearAvgBmiJpaRepository.save(b);
      
      return repositoryBirthYearAvgBmiMapper.to(b);
    })).orElse(null);
  }
  
}
