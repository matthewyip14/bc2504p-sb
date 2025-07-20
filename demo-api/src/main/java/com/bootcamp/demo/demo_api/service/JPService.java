package com.bootcamp.demo.demo_api.service;

import java.util.List;
import com.bootcamp.demo.demo_api.entity.CommentEntity;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface JPService {
  List<UserDTO> getUsers();
  List<UserEntity> getAndSaveUsers();
  List<PostEntity> getAndSavePosts();
  List<CommentEntity> getAndSaveComments();

  List<PostEntity> getPostsByUserId(Long userId) throws JsonProcessingException;

  List<UserEntity> findAllUsers();
}