package controller.employee;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    boolean add(Employee employee) throws SQLException;

    List<Employee> getAll() throws SQLException;

    Employee search(String email) throws SQLException;

    boolean update(Employee employee) throws SQLException;

    boolean delete(String email) throws  SQLException;
}
