package com.project.cls.loan_system.model;

import com.project.cls.loan_system.lib.Gender;
import com.project.cls.loan_system.lib.Occupation;
import lombok.Getter;

@Getter
public class UserDTO {
  private Long id;
  private String name;
  private String username;
  private Gender Gender; // Assuming
  private Integer age;
  private String email;
  private Occupation occupation;
  private Address address;
  private String phone;
  private Company company;

  @Getter
  public static class Company {
    private String name;
    private String phone;
  }

  @Getter
  public static class Address {
    private String disrict;
    private String estate;
    private String buildingName;
  }
}
