package com.bootcamp.demo.demo_exercise_calculator.lib;

import lombok.Getter;

@Getter
public enum Operation {
  ADD("add"),
  SUB("subtract"),
  MUL("multiply"),
  DIV("divide"),;

  private final String value;

  Operation(String value) {
    this.value = value;
  }
  public String getValue() {
    return value;
  }

    public static Operation fromString(String input) {
        for (Operation op : Operation.values()) {
            if (op.getValue().equalsIgnoreCase(input)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unsupported operation: " + input);
    }
}
