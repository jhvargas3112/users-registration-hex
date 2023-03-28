package com.company.signup.infrastructure.repository.db.statistic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.infrastructure.client.jpa.BirthYearAvgBmiJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapper;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapperImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetBirthYearAvgBmiByYearDBRepositoryTest {

  private BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository;

  private RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper;

  private GetBirthYearAvgBmiByYearDBRepository getBirthYearAvgBmiByYearDBRepository;

  @BeforeEach
  public void setUp() {
    this.repositoryBirthYearAvgBmiMapper = mock(RepositoryBirthYearAvgBmiMapper.class);
    this.birthYearAvgBmiJpaRepository = mock(BirthYearAvgBmiJpaRepository.class);
    this.getBirthYearAvgBmiByYearDBRepository = new GetBirthYearAvgBmiByYearDBRepository(
        birthYearAvgBmiJpaRepository, repositoryBirthYearAvgBmiMapper);
  }

  @Test
  public void given_birth_year_avg_bmi_year_then_return_birth_year_avg_bmi() {
    var year = 1990;
    
    var entityBirthYearAvgBmi = new com.company.signup.infrastructure.repository.db.entity.BirthYearAvgBmi(
        1990, 76.24);

    var birthYearAvgBmi = new RepositoryBirthYearAvgBmiMapperImpl().to(entityBirthYearAvgBmi);
    when(birthYearAvgBmiJpaRepository.findByYear(year)).thenReturn(
        Optional.of(entityBirthYearAvgBmi));
    when(repositoryBirthYearAvgBmiMapper.to(entityBirthYearAvgBmi)).thenReturn(birthYearAvgBmi);

    var foundUser = getBirthYearAvgBmiByYearDBRepository.execute(year);

    assert foundUser.map(f -> f.equals(birthYearAvgBmi)).orElse(false);

    verify(birthYearAvgBmiJpaRepository, times(1)).findByYear(year);
    verify(repositoryBirthYearAvgBmiMapper, times(1)).to(entityBirthYearAvgBmi);
  }

}
