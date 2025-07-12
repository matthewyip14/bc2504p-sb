package com.bootcamp.demo.demo_api.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.demo.demo_api.dto.HSBCUserDTO;

public interface HSBCOperation {
  @GetMapping(value = "/hsbc/users")
  List<HSBCUserDTO> getUsers();
}