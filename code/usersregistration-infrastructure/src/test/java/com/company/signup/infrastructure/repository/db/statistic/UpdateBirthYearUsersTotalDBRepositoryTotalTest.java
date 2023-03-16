package com.company.signup.infrastructure.repository.db.statistic;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.model.statistic.BirthYearUsersTotal;
import com.company.signup.infrastructure.client.jpa.BirthYearUsersTotaJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.BirthYearUsersTotalMapper;
import com.company.signup.infrastructure.repository.db.mapper.BirthYearUsersTotalMapperImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdateBirthYearUsersTotalDBRepositoryTotalTest {

  private BirthYearUsersTotaJpaRepository birthYearUsersTotaJpaRepository;

  private BirthYearUsersTotalMapper birthYearUsersTotalMapper;

  private UpdateBirthYearUsersTotalDBRepository updateBirthYearUsersTotalDBRepository;

  @BeforeEach
  public void setUp() {
    this.birthYearUsersTotalMapper = mock(BirthYearUsersTotalMapper.class);
    this.birthYearUsersTotaJpaRepository = mock(BirthYearUsersTotaJpaRepository.class);
    this.updateBirthYearUsersTotalDBRepository = new UpdateBirthYearUsersTotalDBRepository(
        birthYearUsersTotaJpaRepository, birthYearUsersTotalMapper);
  }

  @Test
  public void given_existing_birth_year_users_total_then_birth_year_users_total_updated() {
    var year = 1990;

    var updatedBirthYearUsersTotal = BirthYearUsersTotal.create(year, 5);
    var birthYearUsersTotalToUpdate = new BirthYearUsersTotalMapperImpl().to(
        updatedBirthYearUsersTotal);

    when(birthYearUsersTotaJpaRepository.findByYear(year)).thenReturn(
        Optional.of(birthYearUsersTotalToUpdate));
    when(birthYearUsersTotaJpaRepository.save(birthYearUsersTotalToUpdate)).thenReturn(
        birthYearUsersTotalToUpdate);
    when(birthYearUsersTotalMapper.to(birthYearUsersTotalToUpdate)).thenReturn(
        updatedBirthYearUsersTotal);

    updateBirthYearUsersTotalDBRepository.execute(updatedBirthYearUsersTotal);

    verify(birthYearUsersTotaJpaRepository, times(1)).findByYear(year);
    verify(birthYearUsersTotaJpaRepository, times(1)).save(birthYearUsersTotalToUpdate);
    verify(birthYearUsersTotalMapper, times(1)).to(birthYearUsersTotalToUpdate);
  }

  @Test
  public void given_not_existing_birth_year_users_total_then_birt_year_users_total_not_updated() {
    var year = 1990;

    when(birthYearUsersTotaJpaRepository.findByYear(year)).thenReturn(Optional.empty());

    assertNull(updateBirthYearUsersTotalDBRepository.execute(BirthYearUsersTotal.create(year, 5)));

    verify(birthYearUsersTotaJpaRepository, times(1)).findByYear(year);
  }

}
