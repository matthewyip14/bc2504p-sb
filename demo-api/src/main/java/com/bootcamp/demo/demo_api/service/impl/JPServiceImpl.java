package com.bootcamp.demo.demo_api.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.demo_api.entity.CommentEntity;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.exception.BusinessException;
import com.bootcamp.demo.demo_api.exception.SysError;
import com.bootcamp.demo.demo_api.lib.Scheme;
import com.bootcamp.demo.demo_api.mapper.EntityMapper;
import com.bootcamp.demo.demo_api.model.dto.CommentDTO;
import com.bootcamp.demo.demo_api.model.dto.PostDTO;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;
import com.bootcamp.demo.demo_api.repository.CommentRepository;
import com.bootcamp.demo.demo_api.repository.PostRepository;
import com.bootcamp.demo.demo_api.repository.UserRepository;
import com.bootcamp.demo.demo_api.service.JPService;

@Service
public class JPServiceImpl implements JPService {
  // ! @Value has dependency, complete injection before server start completed.
  @Value("${service-api.jsonplaceholder.host}")
  private String domain;

  @Value("${service-api.jsonplaceholder.endpoints.users}")
  private String usersEndpoint;

  @Value("${service-api.jsonplaceholder.endpoints.posts}")
  private String postsEndpoint;

  @Value("${service-api.jsonplaceholder.endpoints.comments}")
  private String commentsEndpoint;

  @Autowired
  private RestTemplate restTemplate; // built-in library

  @Autowired
  @Qualifier(value = "student") // Use the bean named "tutor"
  private String abc;

  @Autowired
  private UserRepository userRepository; // Repository for UserEntity

  @Autowired
  private PostRepository postRepository; // Repository for PostEntity

  @Autowired
  private CommentRepository commentRepository; // Repository for PostEntity

  @Autowired
  private EntityMapper entityMapper; // Mapper to convert DTOs to Entities

  @Override
  public List<UserDTO> getUsers() {
    // String url = "https://" + domain + usersEndpoint;
    String url = UriComponentsBuilder.newInstance() // Build the URL using UriComponentsBuilder
        .scheme(Scheme.HTTPS.getValue())  // Set the scheme to https
        .host(domain) // Set the host from the property
        .path(usersEndpoint)  // Set the path from the property
        .build()  // Build the URI
        .toUriString(); // Convert to String
        
    System.out.println("url = " + url);
   
    // ! getForObject():
    // 1. call API
    // 2. convert from JSON String to Java object
    // 3. return the object
    UserDTO[] users =  this.restTemplate.getForObject(url, UserDTO[].class);  
    // Make the GET request to the URL and map the response to UserDTO array  
    return Arrays.asList(users);
  }

  @Override
  public List<UserEntity> getAndSaveUsers() {
    // ! Convert List<UserDTO> to List<UserEntity>
    List<UserEntity> userEntities = this.getUsers().stream() //
        .map(e -> {
          return this.entityMapper.map(e); // Use the mapper to convert UserDTO to UserEntity 
        }).collect(Collectors.toList());
    this.userRepository.deleteAll();
    // save to DB
    return this.userRepository.saveAll(userEntities);
  }
  
  @Override
  public List<PostEntity> getAndSavePosts() {
    List<PostEntity> postEntities = this.getPosts().stream()
        .map(e -> { // Map PostDTO to PostEntity
          System.out.println("userid " + e.getUserId());

          UserEntity userEntity = this.userRepository.findByJphUserId(e.getUserId())
              .orElseThrow(() -> new BusinessException(SysError.USER_NOT_FOUND));
          return this.entityMapper.map(e, userEntity); // Use the mapper to convert PostDTO to PostEntity
        }).collect(Collectors.toList());
    // Save PostEntities to the database
    return this.postRepository.saveAll(postEntities);
  }

  private List<PostDTO> getPosts() {
    // String url = "https://" + domain + usersEndpoint;
    String url = UriComponentsBuilder.newInstance() // Build the URL using UriComponentsBuilder
        .scheme(Scheme.HTTPS.getValue())  // Set the scheme to https
        .host(domain) // Set the host from the property
        .path(postsEndpoint)  // Set the path from the property
        .build()  // Build the URI
        .toUriString(); // Convert to String
    System.out.println("url = " + url);
    PostDTO[] users =  this.restTemplate.getForObject(url, PostDTO[].class);  
    return Arrays.asList(users);
  }

  @Override
  public List<CommentEntity> getAndSaveComments() {
    
   List<CommentEntity> commentEntities = this.getComments().stream()
        .map(e -> { // Map CommentDTO to CommentEntity
        System.out.println("postid " + e.getPostId());

          PostEntity postEntity = this.postRepository.findByJphPostId(e.getPostId())
              .orElseThrow(() -> new RuntimeException("Post not found."));
          return this.entityMapper.map(e, postEntity);
        }).collect(Collectors.toList());
    // Save PostEntities to the database
    return this.commentRepository.saveAll(commentEntities);
  }

  private List<CommentDTO> getComments() {
    // String url = "https://" + domain + usersEndpoint;
    String url = UriComponentsBuilder.newInstance() // Build the URL using UriComponentsBuilder
        .scheme(Scheme.HTTPS.getValue())  // Set the scheme to https
        .host(domain) // Set the host from the property
        .path(commentsEndpoint)  // Set the path from the property
        .build()  // Build the URI
        .toUriString(); // Convert to String
    System.out.println("url = " + url);
    CommentDTO[] users =  this.restTemplate.getForObject(url, CommentDTO[].class);  
    return Arrays.asList(users);
  }

  @Override
  public List<PostEntity> getPostsByUserId(Long userId) {
    UserEntity userEntity = this.userRepository.findById(userId)
    .orElseThrow(() -> new RuntimeException("User not found."));
    // Find posts by UserEntity ID
    return this.postRepository.findByUserEntity(userEntity);
  }

  @Override
  public List<UserEntity> findAllUsers() {
    return this.userRepository.findAll();
  }
}
