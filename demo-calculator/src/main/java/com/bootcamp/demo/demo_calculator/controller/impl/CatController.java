package com.bootcamp.demo.demo_calculator.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootcamp.demo.demo_calculator.controller.CatOperation;
import com.bootcamp.demo.demo_calculator.model.Cat;
import com.bootcamp.demo.demo_calculator.model.Dog;
import com.bootcamp.demo.demo_calculator.service.CatService;

  // ! (Always) Controller -> Service
  // ! (Never) Service -> Controller
@Controller
@ResponseBody
public class CatController implements CatOperation {
  
  
  @Autowired // ! The way to ask Spring manager help to pick up the bean 呼喚
  private CatService catService; // bean

  @Autowired
  private Dog dog;

  @Override
  public Integer dogSum(Integer a, Integer b) {
    return this.dog.sum(a, b);
  }
  

  

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