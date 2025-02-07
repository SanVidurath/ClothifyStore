package controller.order;

import db.DBConnection;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {
    public boolean add(List<OrderDetail> orderDetailList) throws SQLException {
        for (OrderDetail orderDetail : orderDetailList) {
            boolean isAddedOrderDetail = add(orderDetail);
            if(!isAddedOrderDetail){
                return false;
            }
        }
        return true;
    }

    public boolean add(OrderDetail orderDetail) throws SQLException {
        String sql = "Insert into orderdetail values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, new OrderController().getLastId());
        preparedStatement.setObject(2, orderDetail.getProductCode());
        preparedStatement.setObject(3, orderDetail.getUnitPrice());
        preparedStatement.setObject(4, orderDetail.getQuantityPurchased());
        return preparedStatement.executeUpdate() > 0;
    }
}
