package controller.order;

import controller.product.ProductController;
import db.DBConnection;
import model.Order;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private final List<OrderDetail> orderDetailList = new ArrayList<>();
    public boolean place(Order order) throws SQLException {
        String sql = "Insert into orders(order_date,emp_id,emp_name,cust_id,total,payment_type) values (?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, order.getDate());
            preparedStatement.setObject(2, order.getEmployeeId());
            preparedStatement.setObject(3, order.getEmployeeName());
            preparedStatement.setObject(4, order.getCustomerId());
            preparedStatement.setObject(5, order.getTotal());
            preparedStatement.setObject(6, order.getPaymentType());
            boolean isAddedOrder = preparedStatement.executeUpdate() > 0;
            if (isAddedOrder) {
                boolean isAddedOrderDetail = new OrderDetailController().add(order.getOrderDetailList());
                if (isAddedOrderDetail) {
                    boolean isUpdatedStock = new ProductController().updateStock(order.getOrderDetailList());
                    if (isUpdatedStock) {
                        connection.commit();
                        return true;
                    }
                }
            }
        }finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    public Integer getLastId() throws SQLException {
        Order last = getLast();
        return last.getId();
    }

    public Order getLast() throws SQLException {
        Order order = null;
        ArrayList<OrderDetail> orderDetailList = new ArrayList<>();
        String sql = "Select * from orders order by id desc limit 1";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            order = new Order(
                    Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    Integer.parseInt(resultSet.getString(3)),
                    resultSet.getString(4),
                    Integer.parseInt(resultSet.getString(5)),
                    Double.parseDouble(resultSet.getString(6)),
                    resultSet.getString(7),
                    orderDetailList
            );
        }
        return order;
    }

    public List<Order> getAll() throws SQLException {
        List<Order> orderList = new ArrayList<>();
        String sql = "Select * from orders";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while(resultSet.next()){
            Order order = new Order(
                    Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    Integer.parseInt(resultSet.getString(3)),
                    resultSet.getString(4),
                    Integer.parseInt(resultSet.getString(5)),
                    Double.parseDouble(resultSet.getString(6)),
                    resultSet.getString(7),
                    orderDetailList);
            orderList.add(order);
        }
        return orderList;
    }
    public List<Integer> getIds() throws SQLException {
        List<Order> orderList = getAll();
        List<Integer> orderIds = new ArrayList<>();
        orderList.forEach(order -> orderIds.add(order.getId()));
        return orderIds;
    }

    public Order search(String orderId) throws SQLException {
        String sql = "Select * from orders where id="+"'"+orderId+"'";
        Order order = null;
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while(resultSet.next()){
            order = new Order(
                    Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    Integer.parseInt(resultSet.getString(3)),
                    resultSet.getString(4),
                    Integer.parseInt(resultSet.getString(5)),
                    Double.parseDouble(resultSet.getString(6)),
                    resultSet.getString(7),
                    orderDetailList);
        }
        return order;
    }
}
