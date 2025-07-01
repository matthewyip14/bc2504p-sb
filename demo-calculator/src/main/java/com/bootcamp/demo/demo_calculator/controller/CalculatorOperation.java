package com.bootcamp.demo.demo_calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.demo_calculator.model.Cat;

public interface CalculatorOperation {
  // ! two ways for parameters

  // ! 1. Path Variable
  // http://localhost:8080/sum/3/4
  @GetMapping(value = "/sum/{x}/{y}")
  Integer sum(@PathVariable Integer x, @PathVariable Integer y);

  // given name and age
  // return cat

  // http://localhost:8080/cat/John/14
  @GetMapping(value = "/cat/{name}/{age}")
  Cat getCat(@PathVariable Integer age, @PathVariable String name);

  // http://localhost:8080/cat2/14/John
  @GetMapping(value = "/cat2/{x}/{y}")
  Cat getCat2(@PathVariable(value = "x") Integer age,
      @PathVariable(value = "y") String name);

  // ! 2. Request Parameter
  // http://localhost:8080/subtract?x=9&y=6
  @GetMapping(value = "/subtract")
  Integer subtract(@RequestParam Integer x, @RequestParam Integer y);

  // http://localhost:8080/cat3?x=John&y=10
  @GetMapping(value = "/cat3")
  Cat getCat3(@RequestParam(value = "x") String name,
      @RequestParam(value = "y") Integer age);
}