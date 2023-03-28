package com.company.signup.infrastructure.repository.db.statistic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.infrastructure.client.jpa.BirthYearAvgBmiJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapper;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaveBirthYearAvgBmiDBRepositoryTest {

  private BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository;

  private RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper;

  private SaveBirthYearAvgBmiDBRepository saveBirthYearAvgBmiDBRepository;

  @BeforeEach
  public void setUp() {
    this.repositoryBirthYearAvgBmiMapper = mock(RepositoryBirthYearAvgBmiMapper.class);
    this.birthYearAvgBmiJpaRepository = mock(BirthYearAvgBmiJpaRepository.class);
    this.saveBirthYearAvgBmiDBRepository = new SaveBirthYearAvgBmiDBRepository(
        birthYearAvgBmiJpaRepository, repositoryBirthYearAvgBmiMapper);
  }

  @Test
  public void given_birth_year_avg_bmi_then_birth_year_avg_bmi_saved() {
    var birthYearAvgBmi = BirthYearAvgBmi.create(1990, 76.24);
    var entityBirthYearAvgBmi = new RepositoryBirthYearAvgBmiMapperImpl().to(birthYearAvgBmi);

    when(repositoryBirthYearAvgBmiMapper.to(birthYearAvgBmi)).thenReturn(entityBirthYearAvgBmi);
    when(birthYearAvgBmiJpaRepository.save(entityBirthYearAvgBmi)).thenReturn(
        entityBirthYearAvgBmi);
    when(repositoryBirthYearAvgBmiMapper.to(entityBirthYearAvgBmi)).thenReturn(birthYearAvgBmi);

    saveBirthYearAvgBmiDBRepository.execute(birthYearAvgBmi);

    verify(repositoryBirthYearAvgBmiMapper, times(1)).to(birthYearAvgBmi);
    verify(birthYearAvgBmiJpaRepository, times(1)).save(entityBirthYearAvgBmi);
    verify(repositoryBirthYearAvgBmiMapper, times(1)).to(entityBirthYearAvgBmi);
  }


}
