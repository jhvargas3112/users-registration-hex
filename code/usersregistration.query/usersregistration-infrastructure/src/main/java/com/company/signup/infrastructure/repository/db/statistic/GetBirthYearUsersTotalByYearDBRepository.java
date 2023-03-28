package com.company.signup.infrastructure.repository.db.statistic;

import com.company.signup.domain.model.statistic.BirthYearUsersTotal;
import com.company.signup.domain.repository.statisctic.GetBirthYearUsersTotalByYearRepository;
import com.company.signup.infrastructure.client.jpa.BirthYearUsersTotaJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.BirthYearUsersTotalMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GetBirthYearUsersTotalByYearDBRepository implements
    GetBirthYearUsersTotalByYearRepository {

  private final BirthYearUsersTotaJpaRepository birthYearUsersTotaJpaRepository;

  private final BirthYearUsersTotalMapper birthYearUsersTotalMapper;

  public GetBirthYearUsersTotalByYearDBRepository(
      BirthYearUsersTotaJpaRepository birthYearUsersTotaJpaRepository,
      BirthYearUsersTotalMapper birthYearUsersTotalMapper) {
    this.birthYearUsersTotaJpaRepository = birthYearUsersTotaJpaRepository;
    this.birthYearUsersTotalMapper = birthYearUsersTotalMapper;
  }

  @Override
  public Optional<BirthYearUsersTotal> execute(Integer year) {
    return birthYearUsersTotaJpaRepository.findByYear(year).map(birthYearUsersTotalMapper::to);
  }

}
