package controller.product;

import db.DBConnection;
import model.ProductDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailController {

    public boolean addProductDetail(ProductDetail productDetail) throws SQLException {
        String sql = "Insert into productdetail values (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,productDetail.getProdCode());
        preparedStatement.setObject(2,productDetail.getSupId());
        preparedStatement.setObject(3,productDetail.getUnitPrice());
        preparedStatement.setObject(4,productDetail.getQtySupplied());
        return preparedStatement.executeUpdate()>0;
    }

    public List<ProductDetail> getAll() throws SQLException {
        ArrayList<ProductDetail> productDetailList = new ArrayList<>();
        String sql = "Select * from productdetail";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()){
            ProductDetail productDetail = new ProductDetail(Integer.parseInt(resultSet.getString(1)), Integer.parseInt(resultSet.getString(2)), Double.parseDouble(resultSet.getString(3)), Integer.parseInt(resultSet.getString(4)));
            productDetailList.add(productDetail);
        }
        return productDetailList;
    }

    public boolean delete(Integer code) throws SQLException {
        String sql = "Delete from productdetail where prod_code='"+code+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.createStatement().executeUpdate(sql)>0;
    }
}
