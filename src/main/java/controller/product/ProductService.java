package controller.product;

import controller.model.Product;
import controller.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    boolean add(Product product) throws SQLException;

    List<Product> getAll() throws SQLException;

    Product search(Integer productCode) throws SQLException;

    boolean update(Product product) throws SQLException;

    boolean delete(Integer code) throws SQLException;
}
