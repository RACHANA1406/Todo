package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.todo.service.TodoH2Service;
import com.example.todo.model.Todo;

@RestController
public class TodoController {

    @Autowired
    public TodoH2Service ts;

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable("id") int id) {
        ts.deleteTodo(id);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        return ts.updateTodo(id, todo);
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo) {
        return ts.addTodo(todo);
    }

    @GetMapping("/todos/{id}")
    public Todo getById(@PathVariable("id") int id) {
        return ts.getById(id);
    }

    @GetMapping("/todos")
    public ArrayList<Todo> getTodos() {
        return ts.getTodos();
    }

}
