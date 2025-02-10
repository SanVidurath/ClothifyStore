package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService{
    public boolean add(Customer customer) throws SQLException {
        String sql = "Insert into customers(cust_name,email,phone_no) values(?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,customer.getName());
        preparedStatement.setObject(2,customer.getEmail());
        preparedStatement.setObject(3,customer.getPhoneNumber());
        return preparedStatement.executeUpdate()>0;

    }

    @Override
    public Customer search(String email) throws SQLException {
        Customer customer=null;
        String sql = "Select * from customers where email='"+email+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()){
             customer = new Customer(
                    Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return customer;
    }

    public Customer search(Integer id) throws SQLException {
        Customer customer=null;
        String sql = "Select * from customers where id='"+id+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()){
            customer = new Customer(
                    Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return customer;
    }

    public List<Customer> getAll() throws SQLException {
        ArrayList<Customer> customerList = new ArrayList<>();
        String sql = "Select * from customers";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            Customer customer = new Customer(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            customerList.add(customer);
        }
        return customerList;
    }

    public ObservableList<Integer> getCustomerIds() throws SQLException {
        ObservableList<Integer> customerIds = FXCollections.observableArrayList();
        List<Customer> customerList = getAll();
        customerList.forEach(customer -> customerIds.add(customer.getId()));
        return customerIds;
    }
}
