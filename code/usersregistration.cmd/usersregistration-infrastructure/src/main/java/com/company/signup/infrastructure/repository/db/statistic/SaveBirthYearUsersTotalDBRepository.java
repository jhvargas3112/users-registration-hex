package com.company.signup.infrastructure.repository.db.statistic;

import com.company.signup.domain.model.statistic.BirthYearUsersTotal;
import com.company.signup.domain.repository.statisctic.SaveBirthYearUsersTotalRepository;
import com.company.signup.infrastructure.client.jpa.BirthYearUsersTotaJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.BirthYearUsersTotalMapper;
import org.springframework.stereotype.Service;

@Service
public class SaveBirthYearUsersTotalDBRepository implements SaveBirthYearUsersTotalRepository {

  private final BirthYearUsersTotaJpaRepository birthYearUsersTotaJpaRepository;

  private final BirthYearUsersTotalMapper birthYearUsersTotalMapper;

  public SaveBirthYearUsersTotalDBRepository(
      BirthYearUsersTotaJpaRepository birthYearUsersTotaJpaRepository,
      BirthYearUsersTotalMapper birthYearUsersTotalMapper) {
    this.birthYearUsersTotaJpaRepository = birthYearUsersTotaJpaRepository;
    this.birthYearUsersTotalMapper = birthYearUsersTotalMapper;
  }

  @Override
  public BirthYearUsersTotal execute(BirthYearUsersTotal birthYearUsersTotal) {
    var savedBirthYearUsersTotal = birthYearUsersTotalMapper.to(birthYearUsersTotal);

    birthYearUsersTotaJpaRepository.save(savedBirthYearUsersTotal);

    return birthYearUsersTotalMapper.to(savedBirthYearUsersTotal);
  }
}
