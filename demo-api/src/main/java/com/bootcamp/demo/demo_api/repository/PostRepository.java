package com.bootcamp.demo.demo_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_api.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    // This interface will automatically provide CRUD operations for PostEntity
    // No additional methods are needed unless specific queries are required
  Optional<PostEntity> findByPostId(Long Id);
}
