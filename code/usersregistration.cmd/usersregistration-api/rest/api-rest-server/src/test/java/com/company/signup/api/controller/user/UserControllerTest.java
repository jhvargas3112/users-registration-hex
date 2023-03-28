package com.company.signup.api.controller.user;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.company.signup.api.dto.ResultsPageDTO;
import com.company.signup.api.dto.UserDTO;
import com.company.signup.api.mapper.UserMapper;
import com.company.signup.api.mapper.UserResultsPageMapper;
import com.company.signup.domain.ResultsPage;
import com.company.signup.domain.model.user.BirthDate;
import com.company.signup.domain.model.user.BodyMeasurements;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.usecase.user.AddUserUseCase;
import com.company.signup.domain.usecase.user.GetPaginatedAndOrderedUsersUseCase;
import com.company.signup.domain.usecase.user.GetUserByIdUseCase;
import com.company.signup.domain.usecase.user.UpdateUserUseCase;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

  @MockBean
  private AddUserUseCase addUserUseCase;

  @MockBean
  private UpdateUserUseCase updateUserUseCase;

  @MockBean
  private GetUserByIdUseCase getUserByIdUseCase;

  @MockBean
  private GetPaginatedAndOrderedUsersUseCase getPaginatedAndOrderedUsersUseCase;

  @MockBean
  private UserMapper userMapper;

  @MockBean
  private UserResultsPageMapper userResultsPageMapper;

  @Autowired
  private MockMvc mvc;

  @BeforeEach
  public void setUpBefore() {
    var birtDate = LocalDate.of(1988, 12, 31);

    List<User> users = new ArrayList<>();
    users.add(new User(1L, "pepelucho1", "123456", BirthDate.create(birtDate),
        BodyMeasurements.create(1.87, 82.0)));
    users.add(new User(2L, "pepelucho2", "1234567", BirthDate.create(birtDate),
        BodyMeasurements.create(1.68, 70.0)));
    ResultsPage<User> resultsPage = new ResultsPage<>(users, 2L, 5, 0, 1);

    List<UserDTO> usersDTOs = new ArrayList<>();
    usersDTOs.add(new UserDTO(1L, "pepelucho1", "123456", birtDate, 1.87, 82.0));
    usersDTOs.add(new UserDTO(2L, "pepelucho2", "1234567", birtDate, 1.68, 70.0));
    ResultsPageDTO<UserDTO> resultsPageDTO = new ResultsPageDTO<>(usersDTOs, 2L, 5, 0, 1);

    when(getPaginatedAndOrderedUsersUseCase.execute(0, 5, "birthDate")).thenReturn(resultsPage);
    when(userResultsPageMapper.to(resultsPage)).thenReturn(resultsPageDTO);

    User user = User.create(null, "pepelucho", "123456", BirthDate.create(LocalDate.now()),
        BodyMeasurements.create(1.87, 111.0));

    when(addUserUseCase.execute(any())).thenReturn(user);
  }

  @Test
  public void given_create_user_call_then_return_the_link_of_the_created_user()
      throws Exception {
    String body = """
        {
          "userName": "pepelucho",
          "password": "123456",
          "birthDate": "1995-12-31",
          "height": 1.87,
          "weight": 111
        }""";

    MvcResult result = mvc.perform(
        post("/users").accept((MediaType.APPLICATION_JSON)).content(body)
            .contentType(MediaType.APPLICATION_JSON)).andReturn();

    MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    assertEquals("http://localhost/users/pepelucho",
        response.getHeader(HttpHeaders.LOCATION));
  }

  @Test
  public void given_create_user_call_with_under_18_user_then_return_a_bad_request_http_response_code()
      throws Exception {
    String body = """
        {
          "userName": "pepelucho",
          "password": "123456",
          "birthDate": "2023-01-01",
          "height": 1.87,
          "weight": 111
        }""";

    MvcResult result = mvc.perform(
        post("/users").accept((MediaType.APPLICATION_JSON)).content(body)
            .contentType(MediaType.APPLICATION_JSON)).andReturn();

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
  }

  @Test
  public void given_get_users_call_then_return_json_array_with_users()
      throws Exception {

    var birtDate = LocalDate.of(1988, 12, 31);

    mvc.perform(get("/users?page=0&size=5&sort=birthDate")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isMap())
        .andExpect(jsonPath("$.elements").isArray())
        .andExpect(jsonPath("$.elements", hasSize(2)))
        .andExpect(jsonPath("$.elements[0].id", is(1)))
        .andExpect(jsonPath("$.elements[0].userName", is("pepelucho1")))
        .andExpect(jsonPath("$.elements[0].password", is("123456")))
        .andExpect(jsonPath("$.elements[0].birthDate", is(List.of(1988, 12, 31))))
        .andExpect(jsonPath("$.elements[0].height", is(1.87)))
        .andExpect(jsonPath("$.elements[0].weight", is(82.0)))
        .andExpect(jsonPath("$.elements[1].id", is(2)))
        .andExpect(jsonPath("$.elements[1].userName", is("pepelucho2")))
        .andExpect(jsonPath("$.elements[1].password", is("1234567")))
        .andExpect(jsonPath("$.elements[1].birthDate", is(List.of(1988, 12, 31))))
        .andExpect(jsonPath("$.elements[1].height", is(1.68)))
        .andExpect(jsonPath("$.elements[1].weight", is(70.0)))
        .andExpect(jsonPath("$.totalResults", is(2)))
        .andExpect(jsonPath("$.size", is(5)))
        .andExpect(jsonPath("$.currentPage", is(0)))
        .andExpect(jsonPath("$.totalPages", is(1)));
  }

}
