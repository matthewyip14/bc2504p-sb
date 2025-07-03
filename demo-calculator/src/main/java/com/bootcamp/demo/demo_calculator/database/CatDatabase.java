package com.bootcamp.demo.demo_calculator.database;

import java.util.ArrayList;
import java.util.List;
import com.bootcamp.demo.demo_calculator.model.Cat;

// CRUD - create (insert), read, update, delete
public class CatDatabase {
  // ! what do we do for this "final"?
  public static final List<Cat> pool = new ArrayList<>();
}