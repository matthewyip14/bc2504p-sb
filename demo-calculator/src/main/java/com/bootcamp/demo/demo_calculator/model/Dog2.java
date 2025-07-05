package com.bootcamp.demo.demo_calculator.model;

public class Dog2 {
  private int x;
  private int y;

  public Dog2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int sum() {
    return this.x + this.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public static void main(String[] args) {
    Dog2 d1 = new Dog2(2, 4);
    System.out.println(d1.sum()); // 6
    d1.setX(1);
    d1.setY(7);
    System.out.println(d1.sum()); // 8

    Dog2 d2 = new Dog2(1, 7);
    System.out.println(d2.sum()); // 8
  }
}