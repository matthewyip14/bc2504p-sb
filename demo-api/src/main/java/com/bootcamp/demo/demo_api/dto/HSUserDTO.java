package com.bootcamp.demo.demo_api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HSUserDTO {
  private String name;
  private String username;
  private String phone;
  private String website;
}