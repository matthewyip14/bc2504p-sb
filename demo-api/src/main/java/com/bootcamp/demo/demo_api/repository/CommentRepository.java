package com.bootcamp.demo.demo_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_api.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // This interface will automatically provide CRUD operations for CommentsEntity
    // No additional methods are needed unless specific queries are required
    // JPA Method
    //Optional<CommentsEntity> findById(Long id);
  
}
