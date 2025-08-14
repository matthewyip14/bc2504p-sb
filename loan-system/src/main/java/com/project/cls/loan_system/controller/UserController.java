package com.project.cls.loan_system.controller;

import com.project.cls.loan_system.entity.UserEntity;
import com.project.cls.loan_system.model.UserDTO;
import com.project.cls.loan_system.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    
    private final UserRepository userRepository;
    
    /**
     * Create a new user
     */
    @PostMapping
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
        log.info("Creating new user: {}", user.getUsername());
        
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        
        UserEntity savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    
    /**
     * Get user by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        log.info("Getting user by ID: {}", id);
        
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get user by username
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable String username) {
        log.info("Getting user by username: {}", username);
        
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get user by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getUserByEmail(@PathVariable String email) {
        log.info("Getting user by email: {}", email);
        
        Optional<UserEntity> user = userRepository.findByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get all users
     */
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        log.info("Getting all users");
        
        List<UserEntity> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
    
    /**
     * Search users by term
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserEntity>> searchUsers(@RequestParam String term) {
        log.info("Searching users with term: {}", term);
        
        List<UserEntity> users = userRepository.findBySearchTerm(term);
        return ResponseEntity.ok(users);
    }
    
    /**
     * Get users with loans
     */
    @GetMapping("/with-loans")
    public ResponseEntity<List<UserEntity>> getUsersWithLoans() {
        log.info("Getting users with loans");
        
        List<UserEntity> users = userRepository.findUsersWithLoans();
        return ResponseEntity.ok(users);
    }
    
    /**
     * Update user
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @Valid @RequestBody UserEntity userDetails) {
        log.info("Updating user with ID: {}", id);
        
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        UserEntity user = userOptional.get();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        
        UserEntity updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    /**
     * Delete user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Check if username exists
     */
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exists = userRepository.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }
    
    /**
     * Check if email exists
     */
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = userRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
} 