package task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class attendance extends JFrame {
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    private JTextField nameField = new JTextField(20);
    private JTextField positionField = new JTextField(20);
    private JTextField salaryField = new JTextField(20);
    private JTextArea displayArea = new JTextArea(15, 30);

    public attendance() {
        setTitle("Employee Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Position:"));
        panel.add(positionField);

        panel.add(new JLabel("Salary:"));
        panel.add(salaryField);

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addEmployee();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(addButton);

        JButton updateButton = new JButton("Update Employee");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    updateEmployee();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(updateButton);

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteEmployee();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(deleteButton);

        JButton displayButton = new JButton("Display Employees");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    displayEmployees();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(displayButton);

        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addEmployee() throws SQLException {
        String name = nameField.getText();
        String position = positionField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        employeeDAO.addEmployee(name, position, salary);
        JOptionPane.showMessageDialog(this, "Employee added successfully.");
    }

    private void updateEmployee() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
        String name = nameField.getText();
        String position = positionField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        employeeDAO.updateEmployee(id, name, position, salary);
        JOptionPane.showMessageDialog(this, "Employee updated successfully.");
    }

    private void deleteEmployee() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
        employeeDAO.deleteEmployee(id);
        JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
    }

    private void displayEmployees() throws SQLException {
        List<String> employees = employeeDAO.getAllEmployees();
        displayArea.setText("");
        for (String employee : employees) {
            displayArea.append(employee + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new attendance().setVisible(true);
            }
        });
    }
}
