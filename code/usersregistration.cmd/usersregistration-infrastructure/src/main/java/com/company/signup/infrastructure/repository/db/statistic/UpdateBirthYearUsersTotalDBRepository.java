package com.company.signup.infrastructure.repository.db.statistic;

import com.company.signup.domain.model.statistic.BirthYearUsersTotal;
import com.company.signup.domain.repository.statisctic.UpdateBirthYearUsersTotalRepository;
import com.company.signup.infrastructure.client.jpa.BirthYearUsersTotaJpaRepository;
import com.company.signup.infrastructure.repository.db.mapper.BirthYearUsersTotalMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateBirthYearUsersTotalDBRepository implements UpdateBirthYearUsersTotalRepository {

  private final BirthYearUsersTotaJpaRepository birthYearUsersTotaJpaRepository;

  private final BirthYearUsersTotalMapper birthYearUsersTotalMapper;

  public UpdateBirthYearUsersTotalDBRepository(
      BirthYearUsersTotaJpaRepository birthYearUsersTotaJpaRepository,
      BirthYearUsersTotalMapper birthYearUsersTotalMapper) {
    this.birthYearUsersTotaJpaRepository = birthYearUsersTotaJpaRepository;
    this.birthYearUsersTotalMapper = birthYearUsersTotalMapper;
  }

  @Override
  public BirthYearUsersTotal execute(BirthYearUsersTotal birthYearUsersTotal) {
    var year = birthYearUsersTotal.getYear();

    var birthYearUsersTotalToUpdate = birthYearUsersTotaJpaRepository.findByYear(year);

    return birthYearUsersTotalToUpdate.map(b -> {
      b.setTotal(b.getTotal() + 1);
      birthYearUsersTotaJpaRepository.save(b);
      return birthYearUsersTotalMapper.to(b);

    }).orElse(null);
  }

}
