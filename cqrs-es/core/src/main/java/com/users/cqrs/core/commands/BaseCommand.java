package com.users.cqrs.core.commands;

import com.users.resgistration.common.messages.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {

  public BaseCommand(String id) {
    super(id);
  }

}
