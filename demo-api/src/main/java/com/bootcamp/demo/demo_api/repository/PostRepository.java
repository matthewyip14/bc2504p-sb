package com.bootcamp.demo.demo_api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    // This interface will automatically provide CRUD operations for PostEntity
    // No additional methods are needed unless specific queries are required
    // ! JPA Method returns:
    // 1. Optional<PostEntity>
    // 2. PostEntity
    // 3. List<PostEntity>

  Optional<PostEntity> findByJphPostId(Long jphPostId);
  // ! Important (select by Foreign Key)
  List<PostEntity> findByUserEntity(UserEntity userEntity); // Method to find posts by UserEntity ID

  List<PostEntity> findByTitle(String title); // Method to find posts by UserEntity ID
}
