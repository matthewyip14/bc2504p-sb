package com.bootcamp.demo.demo_calculator.model;

import org.springframework.stereotype.Component;

@Component // new Dog()
public class Dog {

  public int sum(int x, int y) {
    return x + y;
  }
  public static void main(String[] args) {
    Dog d1 = new Dog();
    System.out.println(d1.sum(3, 4));

    // 1 + 5

  }
}