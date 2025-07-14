package com.bootcamp.demo.demo_api.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// ! @EnableScheduling -> Spring Manager find a bean with @Scheduled method
@Component
public class AppScheduler {
  @Scheduled(fixedDelay = 1000)
  public void sayHello() throws InterruptedException {
    Long startTime = System.currentTimeMillis();
    Thread.sleep(3000L);
    System.out.println("Hello World! startTime: " + startTime);
  }

  @Scheduled(fixedDelay = 2000)
  public void sayGoodbye() throws InterruptedException {
    Long startTime = System.currentTimeMillis();
    Thread.sleep(3000L);
    System.out.println("Goodbye! startTime: " + startTime);
  }

  @Scheduled(cron = "0 43 21 * * 1#2")
  public void runTask() {
    System.out.println("Testing cron job");
  }
}
