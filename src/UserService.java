package com.example.useraccessmanagement.services;

import com.example.useraccessmanagement.models.User;
import com.example.useraccessmanagement.utils.DatabaseConnection;

import java.sql.*;

public class UserService {
    private Connection connection;

    public UserService() {
        connection = DatabaseConnection.getConnection();
    }

    public void registerUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole());
        statement.executeUpdate();
    }

    public User authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new User(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("role")
            );
        } else {
            return null;
        }
    }
}
