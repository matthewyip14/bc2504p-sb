package com.bootcamp.demo.demo_api.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.demo.demo_api.dto.HSUserDTO;

public interface HSOperarion {
  @GetMapping(value = "/hs/users")
  List<HSUserDTO> getUsers();
}
