package com.bootcamp.demo.demo_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_api.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // This interface will automatically provide CRUD operations for UserEntity
    // No additional methods are needed unless specific queries are required
    // JPA Method
    Optional<UserEntity> findByJphUserId(Long jphUserId);
  

  
}
