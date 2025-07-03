package com.bootcamp.demo.demo_helloworld;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Cat {
  private String name;
  private Integer age;
}