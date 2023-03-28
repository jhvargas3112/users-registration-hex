package com.company.signup.domain.enums;

public enum EventType {

  HEIGHT_WEIGHT_UPDATED(1);

  private final Integer key;

  EventType(Integer key) {
    this.key = key;
  }

  public Integer getKey() {
    return key;
  }

}
