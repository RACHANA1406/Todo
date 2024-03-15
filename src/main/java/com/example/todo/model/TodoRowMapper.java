package com.example.todo.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.*;

import com.example.todo.model.Todo;

public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Todo(
                rs.getInt("id"),
                rs.getString("todo"),
                rs.getString("priority"),
                rs.getString("status"));
    }
}
