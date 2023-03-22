package com.company.signup.api.mapper;

import com.company.signup.api.dto.UserDTO;
import com.company.signup.domain.model.user.User;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(source = "birthDate", target = "birthDate.date")
  @Mapping(source = "height", target = "bodyMeasurements.height")
  @Mapping(source = "weight", target = "bodyMeasurements.weight")
  User to(UserDTO userDTO);

  @InheritInverseConfiguration
  UserDTO to(User user);

  List<UserDTO> to(List<User> users);

}
