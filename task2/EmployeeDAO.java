package task2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public void addEmployee(String name, String position, double salary) throws SQLException {
        String query = "INSERT INTO Employee (name, position, salary) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, position);
            statement.setDouble(3, salary);
            statement.executeUpdate();
        }
    }

    public void updateEmployee(int id, String name, String position, double salary) throws SQLException {
        String query = "UPDATE Employee SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, position);
            statement.setDouble(3, salary);
            statement.setInt(4, id);
            statement.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM Employee WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<String> getAllEmployees() throws SQLException {
        String query = "SELECT * FROM Employee";
        List<String> employees = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String employee = resultSet.getInt("id") + " - " +
                        resultSet.getString("name") + " - " +
                        resultSet.getString("position") + " - " +
                        resultSet.getDouble("salary");
                employees.add(employee);
            }
        }
        return employees;
    }
}
