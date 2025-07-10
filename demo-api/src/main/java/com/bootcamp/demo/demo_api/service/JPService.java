package com.bootcamp.demo.demo_api.service;

import java.util.List;
import com.bootcamp.demo.demo_api.entity.CommentEntity;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;

public interface JPService {
  List<UserDTO> getUsers();
  List<UserEntity> getAndSaveUsers();
  List<PostEntity> getAndSavePosts(); // Method to get and save posts, if needed
  List<CommentEntity> getAndSaveComments();

  List<PostEntity> getPostsByUserId(Long userId); // Method to get posts by User ID
}
