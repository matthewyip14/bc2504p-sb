package com.bootcamp.demo.demo_calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // ! ComponentScan
// ! Component -> @Controller, @Service, @Repository, @Configuration + @Bean, @Component
public class DemoCalculatorApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoCalculatorApplication.class, args);
  }

}