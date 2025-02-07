package controller.supplier;

import model.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService {
    boolean add(Supplier supplier) throws SQLException;

    List<Supplier> getAll() throws SQLException;

    Supplier search(String email) throws SQLException;

    boolean update(Supplier supplier) throws SQLException;

    boolean delete(String email) throws  SQLException;
}
