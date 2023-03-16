package com.company.signup.infrastructure.repository.db.statistic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.signup.infrastructure.client.jpa.BirthYearUsersTotaJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.BirthYearUsersTotalMapper;
import com.company.signup.infrastructure.repository.db.mapper.BirthYearUsersTotalMapperImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetBirthYearUsersTotalByYearDBRepositoryTest {

  private BirthYearUsersTotaJpaRepository birthYearUsersTotaJpaRepository;

  private BirthYearUsersTotalMapper birthYearUsersTotalMapper;

  private GetBirthYearUsersTotalByYearDBRepository getBirthYearUsersTotalByYearDBRepository;

  @BeforeEach
  public void setUp() {
    this.birthYearUsersTotalMapper = mock(BirthYearUsersTotalMapper.class);
    this.birthYearUsersTotaJpaRepository = mock(BirthYearUsersTotaJpaRepository.class);
    this.getBirthYearUsersTotalByYearDBRepository = new GetBirthYearUsersTotalByYearDBRepository(
        birthYearUsersTotaJpaRepository, birthYearUsersTotalMapper);
  }

  @Test
  public void given_birth_year_users_total_year_then_return_birth_year_users_total() {
    var year = 1990;

    var entityBirthYearUsersTotal = new com.company.signup.infrastructure.repository.db.entity.BirthYearUsersTotal(
        1990, 5);

    var birthYearUsersTotal = new BirthYearUsersTotalMapperImpl().to(entityBirthYearUsersTotal);
    when(birthYearUsersTotaJpaRepository.findByYear(year)).thenReturn(
        Optional.of(entityBirthYearUsersTotal));
    when(birthYearUsersTotalMapper.to(entityBirthYearUsersTotal)).thenReturn(birthYearUsersTotal);

    var foundUser = getBirthYearUsersTotalByYearDBRepository.execute(year);

    assert foundUser.map(f -> f.equals(birthYearUsersTotal)).orElse(false);

    verify(birthYearUsersTotaJpaRepository, times(1)).findByYear(year);
    verify(birthYearUsersTotalMapper, times(1)).to(entityBirthYearUsersTotal);
  }

}
