package com.bootcamp.demo.demo_calculator.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_calculator.controller.CatOperation;
import com.bootcamp.demo.demo_calculator.model.Cat;
import com.bootcamp.demo.demo_calculator.model.Dog;
import com.bootcamp.demo.demo_calculator.service.CatService;

  // ! (Always) Controller -> Service
  // ! (Never) Service -> Controller
@RestController // ! @RestController is a combination of @Controller and @ResponseBody
// ! @Controller - used to define a controller in Spring MVC
// ! @ResponseBody - used to indicate that the return value of the method should be bound
// ! to the web response body, rather than being interpreted as a view name
// ! @RestController - a convenience annotation that combines @Controller and @ResponseBody
// ! @RestController is used to create RESTful web services in Spring
// ! @RestController is used to create RESTful web services in Spring Boot
// ! @RestController is used to create RESTful web services in Spring MVC
// ! @RestController is used to create RESTful web services in Spring Framework
// ! @RestController is used to create RESTful web services in Spring WebFlux
// ! @RestController is used to create RESTful web services in Spring Web MVC
@Controller
@ResponseBody
public class CatController implements CatOperation {
  
  // ! DI (Dependency Injection) - Spring will help us to inject the bean
  // ! Autowired - Spring will help us to pick up the bean from the Spring context
  // ! Bean - a Java object that is managed by Spring
  // ! ComponentScan - Spring will scan the package and sub-packages for beans
  // ! @Autowired - Spring will help us to inject the bean into the field
  // ! @Autowired - Spring will help us to inject the bean into the constructor
  // ! @Autowired - Spring will help us to inject the bean into the setter method 
  @Autowired // ! The way to ask Spring manager help to pick up the bean 呼喚
  private CatService catService; // bean

  // @Autowired
  // private Dog dog;

  // @Override
  // public Integer dogSum(Integer a, Integer b) {
  //   return this.dog.sum(a, b);
  // }
  

  

  @Override
  public boolean addCat(Cat cat) {
    return this.catService.addCat(cat);
  }

  // ! SQL: delete where
  // http://localhost:8080/cat?id=1
  @Override
  public boolean removeCatById(Integer catId) {
    return this.catService.removeCatById(catId);
  }

  // ! SQL: delete where
  @Override
  public boolean removeCatByName(String catName) {
    return this.catService.removeCatByName(catName);
  }

  // ! SQL: update
  @Override
  public boolean updateCatName(Integer catId, String newName) {
    return this.catService.updateCatName(catId, newName);
  }

  @Override
  public Long size() {
    return this.catService.getSize();
  }

  @Override
  public List<Cat> getAllCats() {
    return this.catService.getAllCats();
  } 
}