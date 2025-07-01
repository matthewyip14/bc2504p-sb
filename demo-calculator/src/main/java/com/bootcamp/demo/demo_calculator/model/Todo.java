package com.bootcamp.demo.demo_calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Todo {
  private Long id;
  private String title;
  private boolean completed;
}
