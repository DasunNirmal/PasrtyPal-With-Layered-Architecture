package lk.ijse.PastryPal.BO.Custom;

import lk.ijse.PastryPal.BO.SuperBO;
import lk.ijse.PastryPal.dto.OrderDto;
import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {

    String generateNextID() throws SQLException, ClassNotFoundException;

    boolean placeOrder(OrderDto orderDto, List<OrderTm> orderDetailDto) throws SQLException;

    String getOrdersByDate() throws SQLException, ClassNotFoundException;

    String getTotalOrders() throws SQLException, ClassNotFoundException;
}
