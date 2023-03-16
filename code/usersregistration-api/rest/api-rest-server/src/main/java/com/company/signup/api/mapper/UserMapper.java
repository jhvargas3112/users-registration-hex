package com.company.signup.api.mapper;

import com.company.signup.api.dto.UserDTO;
import com.company.signup.domain.model.user.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User to(UserDTO userDTO);

  UserDTO to(User user);

  List<UserDTO> to(List<User> users);

}
