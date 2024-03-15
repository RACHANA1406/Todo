package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.example.todo.repository.TodoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.todo.model.TodoRowMapper;
import com.example.todo.model.Todo;

@Service
public class TodoH2Service implements TodoRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public void deleteTodo(int id) {
        try {
            db.update("DELETE FROM TODOLIST WHERE id=?", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo getById(int id) {
        try {
            Todo todo = db.queryForObject("SELECT * FROM TODOLIST WHERE id=?",
                    new TodoRowMapper(), id);
            return todo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        if (todo.getTodo() != null) {
            db.update("UPDATE TODOLIST SET todo=? WHERE id=?", todo.getTodo(), id);
        }
        if (todo.getStatus() != null) {
            db.update("UPDATE TODOLIST SET status=? WHERE id=?", todo.getStatus(), id);
        }
        if (todo.getPriority() != null) {
            db.update("UPDATE TODOLIST SET priority=? WHERE id=?", todo.getPriority(), id);
        }
        return getById(id);
    }

    @Override
    public Todo addTodo(Todo todo) {
        db.update("INSERT INTO TODOLIST(todo, priority, status) VALUES(?,?,?)", todo.getTodo(), todo.getPriority(),
                todo.getStatus());
        Todo added = db.queryForObject(
                "SELECT * FROM TODOLIST WHERE todo=?", new TodoRowMapper(), todo.getTodo());
        return added;
    }

    @Override
    public ArrayList<Todo> getTodos() {
        List<Todo> list = db.query("SELECT * FROM TODOLIST", new TodoRowMapper());
        ArrayList<Todo> todos = new ArrayList<>(list);
        return todos;
    }
}
