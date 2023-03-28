package com.company.signup.infrastructure.client.jpa;

import com.company.signup.infrastructure.repository.db.entity.BirthYearAvgBmi;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthYearAvgBmiJpaRepository extends JpaRepository<BirthYearAvgBmi, Long> {

  Optional<BirthYearAvgBmi> findByYear(Integer year);

}
