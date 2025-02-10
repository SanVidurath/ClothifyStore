package controller.order;

import model.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    boolean place(Order order) throws SQLException;
    List<Order> getAll() throws SQLException;
    Order search(String orderId) throws SQLException;
}
