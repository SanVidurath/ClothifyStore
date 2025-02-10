package controller.order;

import db.DBConnection;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public OrderDetail getOrderDetail(Object orderId, Object productCode) throws SQLException {
        OrderDetail orderDetail = null;
        String sql = "Select * from orderdetail where order_id='"+orderId+"' and prod_code='"+productCode+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()){
            orderDetail = new OrderDetail(
                    Integer.parseInt(resultSet.getString(1)),
                    Integer.parseInt(resultSet.getString(2)),
                    Double.parseDouble(resultSet.getString(3)),
                    Integer.parseInt(resultSet.getString(4))
            );
        }
        return orderDetail;
    }
}
