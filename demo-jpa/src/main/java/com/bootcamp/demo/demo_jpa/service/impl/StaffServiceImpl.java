package com.bootcamp.demo.demo_jpa.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.demo_jpa.entity.StaffEntity;
import com.bootcamp.demo.demo_jpa.repository.StaffRepository;
import com.bootcamp.demo.demo_jpa.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
  @Autowired
  private StaffRepository staffRepository;
  // ! this is the repository that will be used to access the database

  @Override
  public StaffEntity save(StaffEntity staffEntity) {
    return staffRepository.save(staffEntity); // ! insert into
  }

  @Override
  public Optional<StaffEntity> findById(Long id) {
    return staffRepository.findById(id); // ! select * from where id = ?
  }
  @Override
  public List<StaffEntity> findAll() {
    return staffRepository.findAll(); // ! select * from
  }
  @Override
  public Boolean deleteById(Long id) {
    if (!this.staffRepository.findById(id).isPresent()) 
      return false; // ! if id not found, return false
    this.staffRepository.deleteById(id); // ! delete from where id = ?
      return true; // ! if id found, return true
  }

  @Override
  public StaffEntity updateById(Long id, StaffEntity staffEntity) {
    if (!staffEntity.getId().equals(id)) // ! check if the id of the staffEntity is the same as the id passed in
      throw new IllegalArgumentException("Wrong staff id."); // ! if not, throw exception
    if (!this.staffRepository.findById(id).isPresent()) 
      throw new IllegalArgumentException("id not extists."); // ! if the id is not found, throw exception
    return this.staffRepository.save(staffEntity); // ! set the id of the staffEntity to the id
  }

  @Override
  public StaffEntity updateNameById(Long id, String name) {
    StaffEntity staffEntity = this.staffRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("id not exists.")); // ! if the id is not found, throw exception
    staffEntity.setName(name); // ! set the name of the staffEntity to the name passed in
    return this.staffRepository.save(staffEntity); // ! save the staffEntity to the database
  }
}
