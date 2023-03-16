package com.company.signup.infrastructure.repository.db.statistic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.domain.model.statistic.BirthYearUsersTotal;
import com.company.signup.infrastructure.client.jpa.BirthYearUsersTotaJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.BirthYearUsersTotalMapper;
import com.company.signup.infrastructure.repository.db.mapper.BirthYearUsersTotalMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaveBirthYearUsersTotalDBRepositoryTest {

  private BirthYearUsersTotaJpaRepository birthYearUsersTotaJpaRepository;

  private BirthYearUsersTotalMapper birthYearUsersTotalMapper;

  private SaveBirthYearUsersTotalDBRepository saveBirthYearUsersTotalDBRepository;

  @BeforeEach
  public void setUp() {
    this.birthYearUsersTotalMapper = mock(BirthYearUsersTotalMapper.class);
    this.birthYearUsersTotaJpaRepository = mock(BirthYearUsersTotaJpaRepository.class);
    this.saveBirthYearUsersTotalDBRepository = new SaveBirthYearUsersTotalDBRepository(
        birthYearUsersTotaJpaRepository, birthYearUsersTotalMapper);
  }
  
  @Test
  public void given_birth_year_users_total_then_birth_year_users_total_saved() {
    var birthYearUsersTotal = BirthYearUsersTotal.create(1990, 5);
    var entityBirthYearUsersTotal = new BirthYearUsersTotalMapperImpl().to(birthYearUsersTotal);

    when(birthYearUsersTotalMapper.to(birthYearUsersTotal)).thenReturn(entityBirthYearUsersTotal);
    when(birthYearUsersTotaJpaRepository.save(entityBirthYearUsersTotal)).thenReturn(
        entityBirthYearUsersTotal);
    when(birthYearUsersTotalMapper.to(entityBirthYearUsersTotal)).thenReturn(birthYearUsersTotal);

    saveBirthYearUsersTotalDBRepository.execute(birthYearUsersTotal);

    verify(birthYearUsersTotalMapper, times(1)).to(birthYearUsersTotal);
    verify(birthYearUsersTotaJpaRepository, times(1)).save(entityBirthYearUsersTotal);
    verify(birthYearUsersTotalMapper, times(1)).to(entityBirthYearUsersTotal);
  }
  
}
