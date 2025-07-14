package com.bootcamp.demo.demo_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_api.service.JPService;

// Somewhere is Speing:
// @Autowired(required = false)
// private CommandLineRunner commandLineRunner;

// if (commandLineRunner != null) commandLineRunner.run();

// ! A Class that implements an interface + Bean
@Component
public class AppStartRunner implements CommandLineRunner {

  @Autowired
  private JPService jpService;

  @Override
  public void run(String... args) throws Exception {
    // this.jpService.getAndSaveUsers();
    // System.out.println("AppStartRunner is running...");
  }
}
