package controller.product;

import controller.db.DBConnection;
import controller.model.ProductDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

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
}
