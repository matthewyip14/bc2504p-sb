package com.bootcamp.demo.demo_jpa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.bootcamp.demo.demo_jpa.entity.StaffEntity;
import com.bootcamp.demo.demo_jpa.model.Staff;

public interface StaffOperation {
  @PostMapping(value = "/staff")
  StaffEntity createStaff(@RequestBody Staff staff);
}