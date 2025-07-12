package com.project.cls.loan_system.model;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class User {
  private Long id;
  private String name;
  private String username;
  @Enumerated(EnumType.STRING)
  private Gender Gender; // Assuming
  private Integer age;
  private String email;
  @Enumerated(EnumType.STRING)
  private Occupation occupation;
  private Address address;
  private String phone;
  private Company company;

  @Getter
  public enum Gender {
    MALE,
    FEMALE,
    NON_BINARY,;
  }

  @Getter
  public enum Occupation {
    STUDENT,
    EMPLOYED,
    UNEMPLOYED,
    RETIRED,;
  }

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
