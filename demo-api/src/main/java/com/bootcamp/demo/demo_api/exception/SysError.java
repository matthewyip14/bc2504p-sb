package com.bootcamp.demo.demo_api.exception;

import lombok.Getter;

@Getter
public enum SysError {
  USER_NOT_FOUND(800001, "User Not Found."),
  POST_NOT_FOUND(800002, "Post Not Found."),
  ;

  private int code;
  private String message;

  private SysError(int code, String message) {
    this.code = code;
    this.message = message;
  }

}
