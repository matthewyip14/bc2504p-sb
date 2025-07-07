package com.bootcamp.demo.demo_api.service.impl;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;
import com.bootcamp.demo.demo_api.service.JPService;

@Service
public class JPServiceImpl implements JPService {
  // ! @Value has dependency, complete injection before server start completed.
  @Value("${service-api.jsonplaceholder.host}")
  private String domain;

  @Value("${service-api.jsonplaceholder.endpoints.users}")
  private String usersEndpoint;

  @Autowired
  private RestTemplate restTemplate; // built-in library

  @Autowired
  @Qualifier(value = "student") // explicit bean name
  private String abc;

  @Override
  public List<UserDTO> getUsers() {
    // String url = "https://" + domain + usersEndpoint;
    System.out.println("tutor=" + this.abc);

    String url = UriComponentsBuilder.newInstance() //
        .scheme("https")
        .host(domain) //
        .path(usersEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    UserDTO[] users = this.restTemplate.getForObject(url, UserDTO[].class);
    return Arrays.asList(users);
  }
  
  
  
}