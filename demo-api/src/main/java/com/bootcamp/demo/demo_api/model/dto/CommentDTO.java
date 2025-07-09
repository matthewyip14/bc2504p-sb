package com.bootcamp.demo.demo_api.model.dto;

import lombok.Getter;

@Getter
public class CommentDTO {
  private Long postId; // Foreign key to PostEntity
  private Long id; // Primary key
  private String name; // Commenter's name
  private String email; // Commenter's email
  private String body; // Comment content
  
}
