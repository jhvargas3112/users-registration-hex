package com.company.signup;

import com.company.signup.cli.CLI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class UsersRegistrationApplication {

  public static void main(String[] args) {
    SpringApplication.run(UsersRegistrationApplication.class, args);

    try {
      CLI.show();
    } catch (Exception e) {
      System.out.println("Something went wrong\n");
    } finally {
      CLI.show();
    }
  }

}
