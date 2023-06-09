package com.company.signup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class QueryApplication {

  public static void main(String[] args) {
    SpringApplication.run(QueryApplication.class, args);
  }

}
