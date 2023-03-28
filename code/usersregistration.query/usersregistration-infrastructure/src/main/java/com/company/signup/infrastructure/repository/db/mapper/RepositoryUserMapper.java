package com.company.signup.infrastructure.repository.db.mapper;

import com.company.signup.infrastructure.repository.db.entity.User;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RepositoryUserMapper {

  @Mapping(source = "birthDate.date", target = "birthDate")
  @Mapping(source = "bodyMeasurements.height", target = "height")
  @Mapping(source = "bodyMeasurements.weight", target = "weight")
  User to(com.company.signup.domain.model.user.User user);

  @InheritInverseConfiguration
  com.company.signup.domain.model.user.User to(User user);

  List<com.company.signup.domain.model.user.User> to(List<User> users);

}
