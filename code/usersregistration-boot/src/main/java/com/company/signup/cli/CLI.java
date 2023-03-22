package com.company.signup.cli;

import com.company.signup.domain.model.user.BirthDate;
import com.company.signup.domain.model.user.BodyMeasurements;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.usecase.statistic.GetPaginatedAndOrderedBirthYearsAvgBmisUseCase;
import com.company.signup.domain.usecase.user.AddUserUseCase;
import com.company.signup.domain.usecase.user.GetPaginatedAndOrderedUsersUseCase;
import com.company.signup.domain.usecase.user.UpdateUserUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.LocalDate;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CLI {

  private static AddUserUseCase addUserUseCase;

  private static UpdateUserUseCase updateUserUseCase;

  private static GetPaginatedAndOrderedUsersUseCase getPaginatedAndOrderedUsersUseCase;

  private static GetPaginatedAndOrderedBirthYearsAvgBmisUseCase getPaginatedAndOrderedBirthYearsAvgBmisUseCase;


  @Autowired
  public CLI(AddUserUseCase addUserUseCase, UpdateUserUseCase updateUserUseCase,
      GetPaginatedAndOrderedUsersUseCase getPaginatedAndOrderedUsersUseCase,
      GetPaginatedAndOrderedBirthYearsAvgBmisUseCase getPaginatedAndOrderedBirthYearsAvgBmisUseCase) {
    CLI.addUserUseCase = addUserUseCase;
    CLI.updateUserUseCase = updateUserUseCase;
    CLI.getPaginatedAndOrderedUsersUseCase = getPaginatedAndOrderedUsersUseCase;
    CLI.getPaginatedAndOrderedBirthYearsAvgBmisUseCase = getPaginatedAndOrderedBirthYearsAvgBmisUseCase;
  }

  public static void show() {
    Scanner enter = new Scanner(System.in);
    String choice;

    do {
      var helper = new StringBuilder().append("\nINTRODUCE AN OPTION\n")
          .append("----------------------\n").append("create-user: Create user \n")
          .append("update-user: Update User \n")
          .append("list-users: List all users ordered by birth date \n")
          .append("list-years-avg-bmi: List all birth years avg bmis ordered by avg bmi \n")
          .append("exit: exit \n").toString();

      System.out.println(helper);

      choice = enter.nextLine();

      Scanner info = new Scanner(System.in);

      String password;
      String userName;
      int page;
      int size;
      int year;
      int month;
      int day;
      double height;
      double weight;

      ObjectMapper mapper = new ObjectMapper();
      mapper.findAndRegisterModules();
      mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

      try {
        switch (choice) {
          case "create-user" -> {
            System.out.println("User name: ");
            userName = info.nextLine();
            System.out.println("Password: ");
            password = info.nextLine();
            System.out.println("Birth date: ");
            System.out.println("Year: ");
            year = info.nextInt();
            System.out.println("Month: ");
            month = info.nextInt();
            System.out.println("Day: ");
            day = info.nextInt();
            System.out.println("Height: ");
            height = info.nextDouble();
            System.out.println("Weight: ");
            weight = info.nextDouble();
            var createdUser = addUserUseCase.execute(
                User.create(null, userName, password,
                    BirthDate.create(LocalDate.of(year, month, day)),
                    BodyMeasurements.create(height, weight)));
            System.out.println(
                "User successfully created -> " + mapper.writeValueAsString(createdUser));
          }
          case "update-user" -> {
            System.out.println("Id: ");
            var id = info.nextLong();
            System.out.println("Height: ");
            var newHeight = info.nextDouble();
            System.out.println("Weight: ");
            var newWeight = info.nextDouble();
            var updatedUser = updateUserUseCase.execute(
                User.create(id, null, null, null, BodyMeasurements.create(newHeight, newWeight)));
            System.out.println(
                "User successfully created -> " + mapper.writeValueAsString(updatedUser));
          }
          case "list-users" -> {
            System.out.println("Page: ");
            page = info.nextInt();
            System.out.println("Size: ");
            size = info.nextInt();
            System.out.println(
                "Registered users -> " + mapper.writeValueAsString(
                    getPaginatedAndOrderedUsersUseCase.execute(page, size, "birthDate")));
          }
          case "list-years-avg-bmi" -> {
            System.out.println("Page: ");
            page = info.nextInt();
            System.out.println("Size: ");
            size = info.nextInt();
            System.out.println("Registered users -> " + mapper.writeValueAsString(
                getPaginatedAndOrderedBirthYearsAvgBmisUseCase.execute(page, size, "avgBmi")));
          }
          case "exit" -> System.exit(0);
          default -> {
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    } while (!choice.equalsIgnoreCase("d"));
  }

}
