package com.bootcamp.demo.demo_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_jpa.entity.StaffEntity;

// ! Select Update Insert Delete (CRUD)
@Repository // Bean
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
  // ! Hibernate (Framework)

  // select * from staffs;
  // insert into staffs values (xxx,xxx...);
  // simliar to update, delete
}