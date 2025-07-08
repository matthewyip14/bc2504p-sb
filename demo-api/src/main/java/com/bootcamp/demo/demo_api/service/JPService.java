package com.bootcamp.demo.demo_api.service;

import java.util.List;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;

public interface JPService {
  List<UserDTO> getUsers();
}
