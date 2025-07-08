package com.bootcamp.demo.demo_api.model.dto;

import lombok.Builder;
import lombok.Getter;

// ! Data Transfer Object (DTO) for User
// ! This class is used to transfer user data between layers of the application
// ! It is annotated with @Builder to allow for easy construction of UserDTO objects
@Getter
@Builder
public class UserDTO {
  private int id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  @Getter
  @Builder
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
  }

    @Getter
    @Builder
    public static class Geo {
      private String lat;
      private String lng;
    }
  
  @Getter
  @Builder
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}
