package com.bootcamp.demo.demo_exercise_calculator.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bootcamp.demo.demo_exercise_calculator.model.Calculator;
import com.bootcamp.demo.demo_exercise_calculator.service.CalculatorService;

@RestController
@RequestMapping("/operation")
public class CalculatorController {

    @Autowired
    private CalculatorService service;

    // GET using query parameters
    @GetMapping(value = "/operation?=x={x}&y={y}&operation={operation}")
    public Object getByQuery(@RequestParam double x,
                             @RequestParam double y,
                             @RequestParam String operation) {
        return service.calculate(x, y, operation);
    }

    // POST with JSON payload
    @PostMapping(value = "/operation")
    public Object postOperation(@RequestBody Calculator operation) {
        double x = Double.parseDouble(operation.getX());
        double y = Double.parseDouble(operation.getY());
        return service.calculate(x, y, operation.getOperation());
    }

    // GET using path parameters
    @GetMapping(value = "/operation/       {x}/{y}/{operation}")
    public Object getByPath(@PathVariable double x,
                            @PathVariable double y,
                            @PathVariable String operation) {
        return service.calculate(x, y, operation);
    }
}
