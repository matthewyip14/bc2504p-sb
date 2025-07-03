package com.bootcamp.demo.demo_calculator.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.demo_calculator.database.CatDatabase;
import com.bootcamp.demo.demo_calculator.model.Cat;
import com.bootcamp.demo.demo_calculator.service.CatService;

// ! Spring checks annotation @service is required to create an object 
// ! and then put it into the Spring context
@Service
public class CatServiceImpl implements CatService {
  @Override
  public boolean addCat(Cat cat) {
    return CatDatabase.pool.add(cat);
  }

  @Override
  public boolean removeCatById(Integer catId) {
    return CatDatabase.pool.remove((int) (catId - 1)) != null;
  }

  @Override
  public boolean removeCatByName(String catName) {
    for (int i = 0; i < CatDatabase.pool.size(); i++) {
      if (CatDatabase.pool.get(i).getName().equals(catName)) {
        CatDatabase.pool.remove(i);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateCatName(Integer catId, String newName) {
    boolean found = false;
    for (int i = 0; i < CatDatabase.pool.size(); i++) {
      if (CatDatabase.pool.get(i).getId().equals(catId)) {
        CatDatabase.pool.get(i).setName(newName);
        found = true;
      }
    }
    return found;
  }

  @Override
  public long getSize() {
    return CatDatabase.pool.size();
  }

  @Override
  public List<Cat> getAllCats() {
    return CatDatabase.pool;
  }
}
