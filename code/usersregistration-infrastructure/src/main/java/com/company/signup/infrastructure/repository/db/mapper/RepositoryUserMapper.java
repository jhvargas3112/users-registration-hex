package com.company.signup.infrastructure.repository.db.mapper;

import com.company.signup.infrastructure.repository.db.entity.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepositoryUserMapper {

  User to(com.company.signup.domain.model.user.User user);

  com.company.signup.domain.model.user.User to(User user);

  List<com.company.signup.domain.model.user.User> to(List<User> users);

}
