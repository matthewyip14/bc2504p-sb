package com.bootcamp.demo.demo_api.lib;

import lombok.Getter;

@Getter
public enum SysCode {
  OK(0, "Success"),
  FAIL(999999, "fail."),
  ;
  // final variables
  private final int code;
  private final String message;

  private SysCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
