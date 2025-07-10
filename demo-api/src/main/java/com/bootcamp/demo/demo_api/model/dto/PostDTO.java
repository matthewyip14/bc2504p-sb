package com.bootcamp.demo.demo_api.model.dto;

import lombok.Getter;

@Getter
public class PostDTO {
  private Long jphId; // Post ID from the external API
  private Long userId; // Foreign key to UserEntity
  private Long id; // Primary key
  private String title; // Post title
  private String body;
}
