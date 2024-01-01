package lk.ijse.PastryPal.BO.Custom;

import lk.ijse.PastryPal.BO.SuperBO;
import lk.ijse.PastryPal.dto.OrderDto;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {

    String generateNextID() throws SQLException, ClassNotFoundException;

    boolean placeOrder(OrderDto orderDto) throws SQLException;
}
