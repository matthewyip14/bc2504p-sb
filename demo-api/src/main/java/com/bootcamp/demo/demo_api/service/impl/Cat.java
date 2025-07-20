package com.bootcamp.demo.demo_api.service.impl;

// 8 Wrapper class
public class Cat {
  private final Wing[] wings;

  public static Cat ofFlyable( ) {
    return new Cat(new Wing(), new Wing());
  }

  public static Cat ofNonFlyable() {
    return new Cat(null, null);
  }

  private Cat(Wing leftWing, Wing rightWing) {
    if (leftWing == null || rightWing == null) {
      this.wings = null;
    } else {
      this.wings = new Wing[] {leftWing, rightWing};
    }
  }

  public boolean isFlyable() {
    return this.wings != null;
  }

  public void fly() {

  }

  public static class Wing {

  }

  public static void main(String[] args) {
    Cat c1 = Cat.ofFlyable();
    Cat c2 = Cat.ofNonFlyable();
  }
}