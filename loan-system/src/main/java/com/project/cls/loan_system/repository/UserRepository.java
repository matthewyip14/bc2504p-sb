package com.project.cls.loan_system.repository;

import com.project.cls.loan_system.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByUsername(String username);
    
    Optional<UserEntity> findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM UserEntity u WHERE u.name LIKE %:searchTerm% OR u.email LIKE %:searchTerm%")
    List<UserEntity> findBySearchTerm(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT u FROM UserEntity u WHERE u.id IN (SELECT DISTINCT l.user.id FROM LoanEntity l)")
    List<UserEntity> findUsersWithLoans();
} 