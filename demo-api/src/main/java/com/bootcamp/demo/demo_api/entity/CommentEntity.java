package com.bootcamp.demo.demo_api.entity;

import jakarta.persistence.Column;
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
  @Column(nullable = false) // Ensure this field is not null
  private String name; // Commenter's name
  @Column(nullable = false) // Ensure this field is not null
  private String email; // Commenter's email
  @Column(nullable = false, length = 500) // Limit the length of the comment body
  private String body; // Comment content

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false) // Foreign key column to PostEntity
  private PostEntity postEntity; // Foreign key to PostEntity, assuming comments are linked to posts
}
