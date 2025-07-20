package com.bootcamp.demo.demo_api.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.demo.demo_api.lib.ApiResp;

@RestControllerAdvice // ! Bean
public class GlobalExceptionHandler {
  @ExceptionHandler(value = BusinessException.class)
  public ApiResp<ErrorDTO> catchBusinessException(BusinessException ex) {
    ErrorDTO errorDTO = ErrorDTO.builder() //
        .code(ex.getCode()) //
        .message(ex.getMessage()) //
        .build();
    return ApiResp.<ErrorDTO>builder() //
        .fail() //
        .data(errorDTO) //
        .build();
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ErrorDTO catchRuntimeException(RuntimeException ex) {
    return ErrorDTO.builder() //
        .code(999999) //
        .message(ex.getMessage()) //
        .build();
  }
}