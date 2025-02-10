package controller.order;

import controller.product.ProductController;
import db.DBConnection;
import model.OrderReturn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderReturnsController {
    public boolean add(OrderReturn orderReturn) throws SQLException {
        String sql = "Insert into orderreturns values (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,orderReturn.getOrderId());
            preparedStatement.setObject(2,orderReturn.getProductCode());
            preparedStatement.setObject(3,orderReturn.getQuantityReturned());
            preparedStatement.setObject(4,orderReturn.getDate());
            boolean isAddedOrderReturn = preparedStatement.executeUpdate() > 0;
            if(isAddedOrderReturn){
                boolean isUpdatedStock = new ProductController().updateStock(orderReturn);
                if(isUpdatedStock){
                    connection.commit();
                    return true;
                }
            }
        }finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }
}
