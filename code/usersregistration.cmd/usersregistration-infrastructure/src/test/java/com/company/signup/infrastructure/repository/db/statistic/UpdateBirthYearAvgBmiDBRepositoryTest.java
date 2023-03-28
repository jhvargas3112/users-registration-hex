package com.company.signup.infrastructure.repository.db.statistic;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.infrastructure.client.jpa.BirthYearAvgBmiJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapper;
import com.company.signup.infrastructure.repository.db.mapper.RepositoryBirthYearAvgBmiMapperImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdateBirthYearAvgBmiDBRepositoryTest {

  private BirthYearAvgBmiJpaRepository birthYearAvgBmiJpaRepository;

  private RepositoryBirthYearAvgBmiMapper repositoryBirthYearAvgBmiMapper;

  private UpdateBirthYearAvgBmiDBRepository updateBirthYearAvgBmiDBRepository;

  @BeforeEach
  public void setUp() {
    this.repositoryBirthYearAvgBmiMapper = mock(RepositoryBirthYearAvgBmiMapper.class);
    this.birthYearAvgBmiJpaRepository = mock(BirthYearAvgBmiJpaRepository.class);
    this.updateBirthYearAvgBmiDBRepository = new UpdateBirthYearAvgBmiDBRepository(
        birthYearAvgBmiJpaRepository, repositoryBirthYearAvgBmiMapper);
  }

  @Test
  public void given_existing_birth_year_avg_bmi_then_birth_year_avg_bmi_updated() {
    var year = 1990;

    var updatedBirthYearAvgBmi = BirthYearAvgBmi.create(year, 76.24);
    var birthYearAvgBmiToUpdate = new RepositoryBirthYearAvgBmiMapperImpl().to(
        updatedBirthYearAvgBmi);

    when(birthYearAvgBmiJpaRepository.findByYear(year)).thenReturn(
        Optional.of(birthYearAvgBmiToUpdate));
    when(birthYearAvgBmiJpaRepository.save(birthYearAvgBmiToUpdate)).thenReturn(
        birthYearAvgBmiToUpdate);
    when(repositoryBirthYearAvgBmiMapper.to(birthYearAvgBmiToUpdate)).thenReturn(
        updatedBirthYearAvgBmi);

    updateBirthYearAvgBmiDBRepository.execute(updatedBirthYearAvgBmi);

    verify(birthYearAvgBmiJpaRepository, times(1)).findByYear(year);
    verify(birthYearAvgBmiJpaRepository, times(1)).save(birthYearAvgBmiToUpdate);
    verify(repositoryBirthYearAvgBmiMapper, times(1)).to(birthYearAvgBmiToUpdate);
  }

  @Test
  public void given_not_existing_birth_year_avg_bmi_then_birth_year_avg_bmi_not_updated() {
    var year = 1990;

    when(birthYearAvgBmiJpaRepository.findByYear(year)).thenReturn(Optional.empty());

    assertNull(updateBirthYearAvgBmiDBRepository.execute(BirthYearAvgBmi.create(1990, 76.24)));

    verify(birthYearAvgBmiJpaRepository, times(1)).findByYear(year);
  }

}
