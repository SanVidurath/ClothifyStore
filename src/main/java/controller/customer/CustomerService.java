package controller.customer;

import model.Customer;

import java.sql.SQLException;

public interface CustomerService {
    boolean add(Customer customer) throws SQLException;
    Customer search(String email) throws SQLException;
}
