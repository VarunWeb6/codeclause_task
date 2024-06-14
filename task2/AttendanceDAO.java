package task2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    public void markAttendance(int employeeId, Date date, String status) throws SQLException {
        String query = "INSERT INTO Attendance (employee_id, date, status) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employeeId);
            statement.setDate(2, date);
            statement.setString(3, status);
            statement.executeUpdate();
        }
    }

    public List<String> getAttendanceByEmployee(int employeeId) throws SQLException {
        String query = "SELECT * FROM Attendance WHERE employee_id = ?";
        List<String> attendanceRecords = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String record = resultSet.getInt("id") + " - " +
                            resultSet.getDate("date") + " - " +
                            resultSet.getString("status");
                    attendanceRecords.add(record);
                }
            }
        }
        return attendanceRecords;
    }
}
