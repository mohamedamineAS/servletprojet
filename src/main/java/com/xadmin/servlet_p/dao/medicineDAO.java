package com.xadmin.servlet_p.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.servlet_p.bean.medicine;

public class medicineDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pharmacy";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_MEDICINE_SQL = "INSERT INTO medicine (med_id, med_name, med_comp_name, med_quantity, med_price) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_MEDICINE_BY_ID = "SELECT id, med_id, med_name, med_comp_name, med_quantity, med_price FROM medicine WHERE id = ?";
    private static final String SELECT_ALL_MEDICINES = "SELECT * FROM medicine";
    private static final String DELETE_MEDICINE_SQL = "DELETE FROM medicine WHERE id = ?";
    private static final String UPDATE_MEDICINE_SQL = "UPDATE medicine SET med_id = ?, med_name = ?, med_comp_name = ?, med_quantity = ?, med_price = ? WHERE id = ?";

    public medicineDAO() {
    }

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

    public void insertMedicine(medicine med) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEDICINE_SQL)) {
            preparedStatement.setString(1, med.getMedId());
            preparedStatement.setString(2, med.getMedName());
            preparedStatement.setString(3, med.getMedCompName());
            preparedStatement.setInt(4, med.getMedQuantity());
            preparedStatement.setInt(5, med.getMedPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public medicine selectMedicine(int id) {
        medicine med = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String medId = rs.getString("med_id");
                String medName = rs.getString("med_name");
                String medCompName = rs.getString("med_comp_name");
                int medQuantity = rs.getInt("med_quantity");
                int medPrice = rs.getInt("med_price");
                med = new medicine(medId, medName, medCompName, medQuantity, medPrice);
                med.setId(id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return med;
    }

    public List<medicine> selectAllMedicines() {
        List<medicine> medicines = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICINES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String medId = rs.getString("med_id");
                String medName = rs.getString("med_name");
                String medCompName = rs.getString("med_comp_name");
                int medQuantity = rs.getInt("med_quantity");
                int medPrice = rs.getInt("med_price");
                medicine med = new medicine(medId, medName, medCompName, medQuantity, medPrice);
                med.setId(id);
                medicines.add(med);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return medicines;
    }

    public boolean deleteMedicine(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MEDICINE_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateMedicine(medicine med) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MEDICINE_SQL)) {
            statement.setString(1, med.getMedId());
            statement.setString(2, med.getMedName());
            statement.setString(3, med.getMedCompName());
            statement.setInt(4, med.getMedQuantity());
            statement.setInt(5, med.getMedPrice());
            statement.setInt(6, med.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
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
