package controller.employee;

import controller.db.DBConnection;
import controller.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements EmployeeService {
    public boolean add(Employee employee) throws SQLException {
        String sql = "Insert into employees(emp_name,address,email,phone_no,password) values (?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, employee.getName());
        preparedStatement.setObject(2, employee.getAddress());
        preparedStatement.setObject(3, employee.getEmail());
        preparedStatement.setObject(4, employee.getPhoneNo());
        preparedStatement.setObject(5, employee.getPassword());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        ArrayList<Employee> employeeList = new ArrayList<>();
        String sql = "Select * from employees";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            Employee employee = new Employee(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public Employee search(String email) throws SQLException {
        Employee employee = null;
        String sql = "Select * from employees where email='" + email + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            employee = new Employee(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
        }
        return employee;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        String sql = "Update employees set emp_name=?,address=?,phone_no=?,password=? where email='" + employee.getEmail() + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, employee.getName());
        preparedStatement.setObject(2, employee.getAddress());
        preparedStatement.setObject(3, employee.getPhoneNo());
        preparedStatement.setObject(4, employee.getPassword());
        return preparedStatement.executeUpdate() > 0;

    }

    public boolean update(String email, String newEmail) throws SQLException {
        String sql = "Update employees set email=? where email='" +email+ "'";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, newEmail);
        return preparedStatement.executeUpdate() > 0;

    }

    @Override
    public boolean delete(String email) throws SQLException {
        String sql = "Delete from employees where email='"+email+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.createStatement().executeUpdate(sql)>0;
    }


}
