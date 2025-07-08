package com.bootcamp.demo.demo_api.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;
import com.bootcamp.demo.demo_api.service.JPService;

@Service
public class JPServiceImpl implements JPService {
  
  @Override
  public List<UserDTO> getUsers() {
    String url = "https://jsonplaceholder.typicode.com/users";
    UserDTO[] users = new RestTemplate().getForObject(url, UserDTO[].class);
    return Arrays.asList(users);
    // Note: Ensure that the User class is properly defined in the model package

  }

}
