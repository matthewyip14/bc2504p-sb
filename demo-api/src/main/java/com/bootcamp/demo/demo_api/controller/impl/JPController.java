package com.bootcamp.demo.demo_api.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_api.controller.JPOperation;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;
import com.bootcamp.demo.demo_api.service.JPService;

@RestController
public class JPController implements JPOperation {
  @Autowired
  private JPService jpService;

  @Override
  public List<UserDTO> getAllJPUsers() {
    return jpService.getUsers();
  }
}
