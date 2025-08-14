package com.bootcamp.demo.demo_calculator.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/mac") // ! Sub path
public class MacController {
  @GetMapping(value = "/buy")
  public String buyMac(@RequestParam String model) {
      return "Success. Mac (" + model + ") is ordered.";
  }
}
