package com.bootcamp.demo.demo_jpa.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// JPA + Hibernate -> create table

// ! Entity -> SQL Database (Table) -> Create table xxx (....);
@Entity
@Table(name = "staffs")
@Getter
@AllArgsConstructor
@Builder
public class StaffEntity {
  @Id // Primary Key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  private Long id;

  private String name;

  @Column(name = "join_date")
  private LocalDate joinDate;

  private Double salary;

  private String email;
}