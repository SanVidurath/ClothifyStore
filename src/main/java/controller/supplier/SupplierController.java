package controller.supplier;

import controller.db.DBConnection;
import controller.model.Employee;
import controller.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierController implements SupplierService{
    @Override
    public boolean add(Supplier supplier) throws SQLException {
        String sql = "Insert into suppliers(sup_name,company,email,phone_no) values (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, supplier.getName());
        preparedStatement.setObject(2, supplier.getCompany());
        preparedStatement.setObject(3, supplier.getEmail());
        preparedStatement.setObject(4, supplier.getPhoneNo());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        ArrayList<Supplier> supplierList = new ArrayList<>();
        String sql = "Select * from suppliers";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            Supplier supplier = new Supplier(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            supplierList.add(supplier);
        }
        return supplierList;
    }

    @Override
    public Supplier search(String email) throws SQLException {
        Supplier supplier = null;
        String sql = "Select * from suppliers where email='" + email + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            supplier = new Supplier(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        }
        return supplier;
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        String sql = "Update suppliers set sup_name=?,company=?,email=?,phone_no=? where email='" + supplier.getEmail() + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, supplier.getName());
        preparedStatement.setObject(2, supplier.getCompany());
        preparedStatement.setObject(3, supplier.getEmail());
        preparedStatement.setObject(4, supplier.getPhoneNo());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String email) throws SQLException {
        String sql = "Delete from suppliers where email='"+email+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.createStatement().executeUpdate(sql)>0;
    }

    public ObservableList<Integer> getSupplierIds() throws SQLException {
        ObservableList<Integer> supplierIds = FXCollections.observableArrayList();
        List<Supplier> supplierList = getAll();
        supplierList.forEach(supplier -> supplierIds.add(supplier.getId()));
        return supplierIds;
    }
}
