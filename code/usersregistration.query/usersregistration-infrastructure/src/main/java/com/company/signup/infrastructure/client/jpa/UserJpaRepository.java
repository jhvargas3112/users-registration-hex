package com.company.signup.infrastructure.client.jpa;

import com.company.signup.infrastructure.repository.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String> {

}
