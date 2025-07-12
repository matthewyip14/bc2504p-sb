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
@Table(name = "posts") // Specify the table name
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
  private Long id; // Primary key
  @Column(nullable = false) // Ensure this field is not null
  private Long jphPostId; // Post ID from the external API
  @Column(nullable = false) // Ensure this field is not null
  private Long postId;
  @Column(nullable = false) // Ensure this field is not null
  private String title; // Post title
  @Column(nullable = false) // Ensure this field is not null
  private String body; // Post content

  @ManyToOne
  @JoinColumn(nullable = false, name = "user_id") // Foreign key column
  private UserEntity userEntity; // Many-to-one relationship with UserEntity
}
