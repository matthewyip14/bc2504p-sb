package com.bootcamp.demo.demo_api.model;

import lombok.Getter;

@Getter
public class User {
  private Long id;
  
  @Getter
  public static class Address {

    @Getter
    public static class Geo {

    }
  }

  @Getter
  public static class Company {

  }
}