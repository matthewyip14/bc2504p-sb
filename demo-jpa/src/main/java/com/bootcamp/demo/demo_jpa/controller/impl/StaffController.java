package com.bootcamp.demo.demo_jpa.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_jpa.controller.StaffOperation;
import com.bootcamp.demo.demo_jpa.entity.StaffEntity;
import com.bootcamp.demo.demo_jpa.model.Staff;
import com.bootcamp.demo.demo_jpa.service.StaffService;

@RestController
public class StaffController implements StaffOperation {
  @Autowired
  private StaffService staffService;

  @Override
  public StaffEntity createStaff(Staff staff) {
    // ! convert staff to staffEntity
    StaffEntity staffEntity = StaffEntity.builder() //
        .name(staff.getName()) //
        .email(staff.getEmail()) //
        .joinDate(staff.getJoinDate()) //
        .salary(staff.getSalary()) //
        .build();
    return this.staffService.save(staffEntity);
  }
 
  @Override
  public StaffEntity getStaffById(Long id) {
    return this.staffService.findById(id) //
        .orElseThrow(() -> new IllegalArgumentException("id not exists.")); // ! if the id is not found, throw exception  
  }

 
  @Override
  public List<StaffEntity> getAllStaffs() {
    return this.staffService.findAll(); // ! select * from
  }

  @Override
  public Boolean deleteStaffById(Long id) {
    return this.staffService.deleteById(id); // ! delete from where id = ?
  }

  @Override
  public StaffEntity updateStaffById(Long id, Staff staff) {
    // ! convert staff to staffEntity
    StaffEntity staffEntity = StaffEntity.builder() //
        .id(id) // ! set the id of the staffEntity to the id passed in
        .name(staff.getName()) //
        .email(staff.getEmail()) //
        .joinDate(staff.getJoinDate()) //
        .salary(staff.getSalary()) //
        .build();
    return this.staffService.updateById(id, staffEntity);
  }

  @Override
  public StaffEntity updateStaffNameById(Long id, String name) {
    return this.staffService.updateNameById(id, name); // ! update name by id
  }
}