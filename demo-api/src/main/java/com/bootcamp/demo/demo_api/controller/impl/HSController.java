package com.bootcamp.demo.demo_api.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_api.controller.HSOperarion;
import com.bootcamp.demo.demo_api.dto.HSUserDTO;
import com.bootcamp.demo.demo_api.mapper.DTOMapper;
import com.bootcamp.demo.demo_api.service.JPService;

@RestController
public class HSController implements HSOperarion{
  @Autowired
  private JPService jpService;
  
  @Autowired
  private DTOMapper dtoMapper;

  @Override
  public List<HSUserDTO> getUsers() {
    return this.jpService.findAllUsers().stream() //
        .map(e -> this.dtoMapper.mapToHS(e)) //
        .collect(Collectors.toList());
  }
}
