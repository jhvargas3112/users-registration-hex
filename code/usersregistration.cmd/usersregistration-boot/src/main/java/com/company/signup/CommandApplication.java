package com.company.signup;

import com.company.signup.domain.command.AddUserCommand;
import com.company.signup.domain.command.CommandHandler;
import com.company.signup.domain.command.UpdateUserCommand;
import com.users.cqrs.core.infrastructure.CommandDispatcher;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CommandApplication {

  private final CommandDispatcher commandDispatcher;

  private final CommandHandler commandHandler;

  public CommandApplication(CommandDispatcher commandDispatcher, CommandHandler commandHandler) {
    this.commandDispatcher = commandDispatcher;
    this.commandHandler = commandHandler;
  }

  public static void main(String[] args) {
    SpringApplication.run(CommandApplication.class, args);
  }

  @PostConstruct
  public void registerHandlers() {
    commandDispatcher.registerHandler(AddUserCommand.class, commandHandler::handle);
    commandDispatcher.registerHandler(UpdateUserCommand.class, commandHandler::handle);
  }

}
