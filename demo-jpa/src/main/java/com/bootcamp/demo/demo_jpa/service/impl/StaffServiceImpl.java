package com.bootcamp.demo.demo_jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.demo_jpa.entity.StaffEntity;
import com.bootcamp.demo.demo_jpa.repository.StaffRepository;
import com.bootcamp.demo.demo_jpa.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
  @Autowired
  private StaffRepository staffRepository;

  @Override
  public StaffEntity save(StaffEntity staffEntity) {
    return staffRepository.save(staffEntity); // ! insert into
  }

  // update
  // delete
  // insert many data (saveAll)
  // select where ...
}