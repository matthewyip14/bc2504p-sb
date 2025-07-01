package com.bootcamp.demo.demo_calculator.controller.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_calculator.model.Todo;

@RestController
@RequestMapping(value = "/todos")
public class TodoController {
  private List<Todo> todos = new ArrayList<>();
  private Long nextId = 1L;

  @GetMapping
  public List<Todo> getTodos() {
    return todos;
  }

  @PostMapping
  public Todo addTodo(@RequestBody Todo todo) {
    todo.setId(nextId++);
    todos.add(todo);
    return todo;
  }

  @PutMapping(value = "/todos/{id}")
  public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
    for (Todo todo : todos) {
      if (todo.getId().equals(id)) {
        todo.setTitle(updatedTodo.getTitle());
        todo.setCompleted(updatedTodo.isCompleted());
        return todo;
      }
    }
    return null; // or throw an exception
  }

  @DeleteMapping(value = "/todos/{id}")
  public void deleteTodo(@PathVariable Long id) {
    todos.removeIf(todo -> todo.getId().equals(id));
  }
}
