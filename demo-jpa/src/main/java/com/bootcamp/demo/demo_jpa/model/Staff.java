package com.bootcamp.demo.demo_jpa.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Staff {
  private String name;
  private LocalDate joinDate;
  private Double salary;
  private String email;
}