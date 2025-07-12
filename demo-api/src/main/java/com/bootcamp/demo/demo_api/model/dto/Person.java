package com.bootcamp.demo.demo_api.model.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Person {
  private double height;
  private double weight;

  public Person(double height, double weight) {
    this.height = height;
    this.weight = weight;
  }
  public double getHeight() {
    return height;
  }
  public void setHeight(double height) {
    this.height = height;
  }
  public double getWeight() {
    return weight;
  }
  public void setWeight(double weight) {
    this.weight = weight; 
  }
  public double calculateBMI() {
   BigDecimal divisor = BigDecimal.valueOf(this.height).multiply(BigDecimal.valueOf(this.height));
    return BigDecimal.valueOf(this.weight).divide((divisor), 2, RoundingMode.HALF_UP)
        .doubleValue();
  }
}
