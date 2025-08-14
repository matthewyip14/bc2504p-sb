package com.bootcamp.demo.demo_helloworld;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/iphone") // ! Sub path
public class IphoneController {
  @GetMapping(value = "/buy")
  public String buyIphone(@RequestParam String model) {
      return "Success. iPhone (" + model + ") is ordered.";
  }
}
