package com.bootcamp.demo.demo_calculator.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.bootcamp.demo.demo_calculator.model.Cat;

// ! RESTful API / REST API
public interface CatOperation {
  // ! SQL: insert into
  @PostMapping(value = "/cat")
  boolean addCat(@RequestBody Cat cat);

  // ! SQL: delete where
  // http://localhost:8080/cat?id=1
  @DeleteMapping(value = "/cat/id/{id}")
  boolean removeCatById(@PathVariable(value = "id") Integer catId);

  // ! SQL: delete where
  @DeleteMapping(value = "/cat/name/{name}")
  boolean removeCatByName(@PathVariable(value = "name") String catName);

  // ! SQL: update
  @PutMapping(value = "/cat/id/{id}/name/{name}")
  boolean updateCatName(@PathVariable(value = "id") Integer catId,
      @PathVariable(value = "name") String newName);

  @GetMapping(value = "/cat/size")
  Long size();

  @GetMapping(value = "/cats")
  List<Cat> getAllCats();
}