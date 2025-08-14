package com.bootcamp.demo_thymeleaf.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// ! RESTful API
@RestController // @Controller + @ResponseBody, return JSON body
public class HelloworldController {
  @GetMapping(value = "/hello")
  public String greeting() {
    return "I'm Matthew Yip";
  }
}
