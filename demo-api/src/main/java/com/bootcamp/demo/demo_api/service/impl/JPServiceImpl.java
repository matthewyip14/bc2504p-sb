package com.bootcamp.demo.demo_api.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.demo_api.model.User;
import com.bootcamp.demo.demo_api.service.JPService;

@Service
public class JPServiceImpl implements JPService {

  @Override
  public List<User> getUsers() {
    String url = "https://jsonplaceholder.typicode.com/users";
    User[] users = new RestTemplate().getForObject(url, User[].class);
    return Arrays.asList(users);
  }
}