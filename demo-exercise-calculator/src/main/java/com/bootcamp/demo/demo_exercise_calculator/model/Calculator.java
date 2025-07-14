package com.bootcamp.demo.demo_exercise_calculator.model;

import com.bootcamp.demo.demo_exercise_calculator.lib.Operation;
import lombok.Getter;

@Getter
public class Calculator {
  private String x;
  private String y;
  private String operation;
  

  // public double add() {
  //   return x + y;
  // }
  // public double subtract() {
  //   return x - y;
  // }
  // public double multiply() {
  //   return x * y;
  // }
  // public double divide() throws ArithmeticException {
  //   if (y == 0) {
  //     throw new ArithmeticException("Division by zero is not allowed.");
  //   }
  //   return x / y;
  // }
}
