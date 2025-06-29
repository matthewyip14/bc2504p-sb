package com.bootcamp.demo.demo_helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@ResponseBody
public class HelloController {
  @GetMapping(value = "/hello") // api endpoint
  public String Helloworld() {
      return "Hello. Welcome to Spring Boot.";
  }
}
