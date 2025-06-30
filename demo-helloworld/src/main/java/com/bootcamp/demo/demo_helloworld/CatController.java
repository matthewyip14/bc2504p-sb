package com.bootcamp.demo.demo_helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/cats")
public class CatController {
  private Cat cats;

  // @GetMapping("/one")
  // public Cat getOneCat() {
  //     return new Cat("Jerry", 2, "Siamese");
  // }
  
  // @GetMapping("/all")
  // public List<Cat> getAllCats() {
  //     return List.of(
  //         new Cat("Tom", 3, "Persian"),
  //         new Cat("Jerry", 2, "Siamese"),
  //         new Cat("Spike", 4, "British Shorthair")
  //     );
  // }
}
