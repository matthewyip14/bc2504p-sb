package com.bootcamp.demo.demo_jpa.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
}