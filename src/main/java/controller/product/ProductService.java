package controller.product;

import controller.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    boolean add(Product product) throws SQLException;

    List<Product> getAll() throws SQLException;
}
