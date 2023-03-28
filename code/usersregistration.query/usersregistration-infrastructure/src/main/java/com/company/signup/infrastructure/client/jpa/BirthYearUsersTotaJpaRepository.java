package com.company.signup.infrastructure.client.jpa;

import com.company.signup.infrastructure.repository.db.entity.BirthYearUsersTotal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthYearUsersTotaJpaRepository extends JpaRepository<BirthYearUsersTotal, Long> {

  Optional<BirthYearUsersTotal> findByYear(Integer year);

}
