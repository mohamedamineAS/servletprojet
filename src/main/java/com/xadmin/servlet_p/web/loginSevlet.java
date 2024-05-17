package com.xadmin.servlet_p.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class loginSevlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "";
    
    // SQL query to check if email and password match
    private static final String SQL_CHECK_LOGIN = "SELECT * FROM users WHERE uemail = ? AND upwd = ?";

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user input from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to the database
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            
            // Prepare the SQL statement
            stmt = conn.prepareStatement(SQL_CHECK_LOGIN);
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            // Execute the query
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Authentication successful, redirect to success page
                response.sendRedirect("HomeServlet");
            } else {
                // Authentication failed, redirect back to login page with error message
                response.sendRedirect("login.jsp?error=invalid_credentials");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle any database errors or driver loading errors
            e.printStackTrace();
            throw new ServletException("Database access error", e);
        } finally {
            // Close the database resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
