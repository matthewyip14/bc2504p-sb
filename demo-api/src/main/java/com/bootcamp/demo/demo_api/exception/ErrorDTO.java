package com.bootcamp.demo.demo_api.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDTO {
  private int code;
  private String message;
}
