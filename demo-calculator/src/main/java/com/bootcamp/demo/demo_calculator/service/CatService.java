package com.bootcamp.demo.demo_calculator.service;

import java.util.List;
import com.bootcamp.demo.demo_calculator.model.Cat;

public interface CatService {
  boolean addCat(Cat cat);
  boolean removeCatById(Integer catId);
  boolean removeCatByName(String catName);
  boolean updateCatName(Integer catId, String newName);
  long getSize();
  List<Cat> getAllCats();
}