package com.bootcamp.demo.demo_api.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_api.entity.CommentEntity;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.model.dto.CommentDTO;
import com.bootcamp.demo.demo_api.model.dto.PostDTO;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;

@Component // Bean
public class EntityMapper {
  public PostEntity map(PostDTO postDTO, UserEntity userEntity) {
    return PostEntity.builder()
        .jphPostId(postDTO.getId())
        .id(postDTO.getId())
        .title(postDTO.getTitle())
        .body(postDTO.getBody())
        .userEntity(userEntity) // Set the user entity
        .build();
  }

  public CommentEntity map(CommentDTO commentDTO, PostEntity postEntity) {
    return CommentEntity.builder() //
              .name(commentDTO.getName()) // Comment name
              .email(commentDTO.getEmail()) // Comment email
              .body(commentDTO.getBody()) // Comment body
              .postEntity(postEntity) // Set the foreign key to PostEntity
              .build();
  }

  public UserEntity map(UserDTO userDTO) {
    return UserEntity.builder()
        .jphUserId(userDTO.getId())
        .id(userDTO.getId())
        .name(userDTO.getName())
        .username(userDTO.getUsername())
        .email(userDTO.getEmail())
        .build();
  }
}
