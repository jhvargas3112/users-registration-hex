package com.company.signup.api.mapper;

import com.company.signup.api.dto.ResultsPageDTO;
import com.company.signup.api.dto.UserDTO;
import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserResultsPageMapper {

  ResultsPage<User> to(ResultsPageDTO<UserDTO> resultsPageDTO);

  ResultsPageDTO<UserDTO> to(ResultsPage<User> resultsPage);

}
