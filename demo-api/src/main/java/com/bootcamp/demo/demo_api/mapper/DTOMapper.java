package com.bootcamp.demo.demo_api.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_api.dto.HSBCUserDTO;
import com.bootcamp.demo.demo_api.dto.HSUserDTO;
import com.bootcamp.demo.demo_api.entity.UserEntity;

// idempotency
@Component // Bean // Static method
public class DTOMapper {
  // ! Method Signature = method name + parameters
  public HSBCUserDTO mapToHSBC(UserEntity userEntity) {
    return HSBCUserDTO.builder() //
        .email(userEntity.getEmail()) //
        .name(userEntity.getName()) //
        .username(userEntity.getUsername()) //
        .build();
  }

  public HSUserDTO mapToHS(UserEntity userEntity) {
    return HSUserDTO.builder() //
        .name(userEntity.getName()) //
        .phone(userEntity.getPhone()) //
        .username(userEntity.getUsername()) //
        .website(userEntity.getWebsite()) //
        .build();
  }
}