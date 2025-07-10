package com.bootcamp.demo.demo_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // Assuming the table name is "users"
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // Auto-increment primary key
  @Column(nullable = false) // Ensure this field is not null
  private Long jphUserId;
  @Column(nullable = false) // Ensure this field is not null
  private String name; // User's name
  @Column(nullable = false, name = "user_name") // To avoid conflict with reserved keyword
  private String username; // User's username
  @Column(nullable = false) // Ensure this field is not null
  private String email; // User's email
  // private Address address; // User's address
  @Column(nullable = false) // Ensure this field is not null
  private String phone; // User's phone number
  @Column(nullable = false) // Ensure this field is not null
  private String website; // User's website
  // private Company company; // User's company name

  // private String lag;
  // private String lng; // User's longitude
  // private String street; // User's street
  // private String suite; // User's suite
  // private String city; // User's city
  // private String zipcode; // User's zipcode
  // private String catchPhrase; // User's company catchphrase
  // private String bs; // User's company business strategy
  // private Geo geo; // User's geographical location (could be a JSON string or similar representation
  
}
