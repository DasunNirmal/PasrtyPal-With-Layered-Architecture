package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.dto.OrderDto;

import java.sql.SQLException;

public interface OrderDAO {

    String generateNextOrderID() throws SQLException, ClassNotFoundException;

    boolean placeOrder(OrderDto orderDto) throws SQLException;
}
