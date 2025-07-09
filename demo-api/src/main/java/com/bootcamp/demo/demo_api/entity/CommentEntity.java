package com.bootcamp.demo.demo_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments") // Specify the table name
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
  private Long id; // Primary key
  private String name; // Commenter's name
  private String email; // Commenter's email
  private String body; // Comment content

  @ManyToOne
  @JoinColumn(name = "post_id") // Foreign key column to PostEntity
  private Long postId; // Foreign key to PostEntity, assuming comments are linked to posts
}
