package com.bootcamp.demo.demo_api.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_api.controller.JPOperation;
import com.bootcamp.demo.demo_api.entity.CommentEntity;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;
import com.bootcamp.demo.demo_api.service.JPService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class JPController implements JPOperation {
  @Autowired
  private JPService jpService;

  // ! List<UserDTO> -> Postman (JSON) (Serialization)
  @Override
  public List<UserDTO> getAllJPUsers() {
    return jpService.getUsers();
  }

  @Override
  public List<UserEntity> getAndSaveUsers() {
    return this.jpService.getAndSaveUsers();
  }

  @Override
  public List<PostEntity> getAndSavePosts() {
    // approach 1: normal Java, try-catch handles exception object
    // try {
    // return this.jpService.getAndSavePosts(); // ! throw new RuntimeException
    // }
    return this.jpService.getAndSavePosts();
  }

  @Override
  public List<CommentEntity> getAndSaveComments() {
    return this.jpService.getAndSaveComments();
  }

  @Override
  public List<PostEntity> getPostsByUserId(Long userId) throws JsonProcessingException {
    System.out.println("userid=" + userId);
    return this.jpService.getPostsByUserId(userId);
  }
}