package com.xadmin.servlet_p.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.servlet_p.bean.User;

public class UserDAO {
    private static final String INSERT_USERS_SQL = "INSERT INTO users (id, uname, upwd, uemail, umobile) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET uname = ?, upwd = ?, uemail = ?, umobile = ? WHERE id = ?";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";

    private String jdbcURL = "jdbc:mysql://localhost:3306/pharmacy";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    public UserDAO() {}

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getNumber());
            preparedStatement.executeUpdate();
        }
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getNumber());
            preparedStatement.setInt(5, user.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
               ResultSet rs = preparedStatement.executeQuery();
               while (rs.next()) {
                   int id = rs.getInt("id");
                   String uname = rs.getString("uname");
                   String upwd = rs.getString("upwd");
                   String uemail = rs.getString("uemail");
                   String umobile = rs.getString("umobile");
                   users.add(new User(id, uname, upwd, uemail, umobile));
               }
           } catch (SQLException e) {
               printSQLException(e);
           }
           return users;
       }

       public User getUser(int id) {
           User user = null;
           try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
               preparedStatement.setInt(1, id);
               ResultSet rs = preparedStatement.executeQuery();
               if (rs.next()) {
                   String uname = rs.getString("uname");
                   String upwd = rs.getString("upwd");
                   String uemail = rs.getString("uemail");
                   String umobile = rs.getString("umobile");
                   user = new User(id, uname, upwd, uemail, umobile);
               }
           } catch (SQLException e) {
               printSQLException(e);
           }
           return user;
       }

       private void printSQLException(SQLException ex) {
           for (Throwable e : ex) {
               if (e instanceof SQLException) {
                   e.printStackTrace(System.err);
                   System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                   System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                   System.err.println("Message: " + e.getMessage());
                   Throwable t = ex.getCause();
                   while (t != null) {
                       System.out.println("Cause: " + t);
                       t = t.getCause();
                   }
               }
           }
       }
   }