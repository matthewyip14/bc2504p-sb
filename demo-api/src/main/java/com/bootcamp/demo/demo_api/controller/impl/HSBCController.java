package com.bootcamp.demo.demo_api.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_api.controller.HSBCOperation;
import com.bootcamp.demo.demo_api.dto.HSBCUserDTO;
import com.bootcamp.demo.demo_api.lib.ApiResp;
import com.bootcamp.demo.demo_api.mapper.DTOMapper;
import com.bootcamp.demo.demo_api.service.JPService;

@RestController
public class HSBCController implements HSBCOperation {
  @Autowired
  private JPService jpService;
  
  @Autowired
  private DTOMapper dtoMapper;

  @Override
  public ApiResp<List<HSBCUserDTO>> getUsers() {
    List<HSBCUserDTO> hsbcUserDTOs = this.jpService.findAllUsers().stream() //
        .map(e -> this.dtoMapper.mapToHSBC(e)) //
        .collect(Collectors.toList());
    return ApiResp.<List<HSBCUserDTO>>builder() //
                  .ok()
                  .data(hsbcUserDTOs)
                  .build();

  }
}
