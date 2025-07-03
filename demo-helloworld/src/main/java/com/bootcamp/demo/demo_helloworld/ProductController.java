package com.bootcamp.demo.demo_helloworld;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController
public class ProductController {

    @GetMapping("/products")
    public List<Map<String, Object>> getProducts() {
        List<Map<String, Object>> productList = new ArrayList<>();

        Map<String, Object> product1 = new HashMap<>();
        product1.put("name", "Laptop");
        product1.put("price", new BigDecimal("12500.50"));

        Map<String, Object> product2 = new HashMap<>();
        product2.put("name", "Headphones");
        product2.put("price", new BigDecimal("799.99"));

        productList.add(product1);
        productList.add(product2);

        return productList;
    }
}

