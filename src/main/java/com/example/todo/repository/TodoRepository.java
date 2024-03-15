package com.example.todo.repository;

import com.example.todo.model.Todo;
import java.util.*;

public interface TodoRepository {
    ArrayList<Todo> getTodos();

    Todo addTodo(Todo todo);

    Todo updateTodo(int id, Todo todo);

    Todo getById(int id);

    void deleteTodo(int id);
}