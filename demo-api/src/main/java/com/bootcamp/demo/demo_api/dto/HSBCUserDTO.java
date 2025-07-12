package com.bootcamp.demo.demo_api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HSBCUserDTO {
  private String name;
  private String username;
  private String email;
}