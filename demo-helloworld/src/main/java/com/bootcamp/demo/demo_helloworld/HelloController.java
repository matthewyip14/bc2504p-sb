package com.bootcamp.demo.demo_helloworld;

import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@ResponseBody
public class HelloController {
  @GetMapping(value = "/hello") // api endpoint (avoid duplicate endpoint URL)
  public String Helloworld() {
      return "Hello. Welcome to Spring Boot.";
  }
  @GetMapping(value = "/goodbye") // api endpoint with parameter
  public String Goodbye() {
      return "Goodbye. See you again.";
  }
  // ! update URL, try again
  // ! update return type, return different type (BigDecimal)
  // ! create one more api, return cat object
  // ! create one more api, return List<Cat> object
  // ! how about hashmap?
  // ! Return ShoppingMall
}
