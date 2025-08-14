package com.bootcamp.demo.demo_helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CalculatorController {
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping(value = "/hello/sum/{x}/{y}")
  public int sum(@PathVariable int x, @PathVariable int y) {
      String url = "http://demo-calculator-app:8081/sum/" + x + "/" +y;
       return restTemplate.getForObject(url, Integer.class);
  }
}
