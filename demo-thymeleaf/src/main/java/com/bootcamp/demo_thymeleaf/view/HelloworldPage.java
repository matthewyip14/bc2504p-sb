package com.bootcamp.demo_thymeleaf.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloworldPage {
  @GetMapping(value = "/hellopage")
  public String helloPage(Model model) {
    model.addAttribute("message", "!!!");
      return "helloworld"; // html file name
  }
  
}
