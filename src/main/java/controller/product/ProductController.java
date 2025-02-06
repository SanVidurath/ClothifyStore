package controller.product;

import controller.db.DBConnection;
import controller.model.Product;
import controller.model.ProductDetail;
import controller.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController implements ProductService {
    public boolean add(Product product) throws SQLException {
        String sql = "Insert into products(prod_descr,category,size,unit_price,qty_in_stock,sup_id) values(?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, product.getDescription());
            preparedStatement.setObject(2, product.getCategory());
            preparedStatement.setObject(3, product.getSize());
            preparedStatement.setObject(4, product.getUnitPrice());
            preparedStatement.setObject(5, product.getQuantityInStock());
            preparedStatement.setObject(6, product.getSupplierId());
            boolean isProductAdded = preparedStatement.executeUpdate() > 0;
            if (isProductAdded) {
                Product lastInsertedProduct = getLast();
                if (lastInsertedProduct != null) {
                    ProductDetail productDetail = new ProductDetail(lastInsertedProduct.getCode(), lastInsertedProduct.getSupplierId(), lastInsertedProduct.getUnitPrice(), lastInsertedProduct.getQuantityInStock());
                    boolean isProductDetailAdded = new ProductDetailController().addProductDetail(productDetail);
                    if (isProductDetailAdded) {
                        connection.commit();
                        return true;
                    }
                }

            }
        } finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();
        String sql = "Select * from products";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            Product product = new Product(Integer.parseInt(resultSet.getString(1)),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),Double.parseDouble(resultSet.getString(5)),Integer.parseInt(resultSet.getString(6)),Integer.parseInt(resultSet.getString(7)));
            productList.add(product);
        }
        return productList;

    }

    private Product getLast() throws SQLException {
        Product product = null;
        String sql = "select * from products group by prod_code order by prod_code desc limit 1";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            product = new Product(Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    Double.parseDouble(resultSet.getString(5)),
                    Integer.parseInt(resultSet.getString(6)),
                    Integer.parseInt(resultSet.getString(7)));
        }
        return product;
    }

}
