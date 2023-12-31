package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.DAO.CrudDAO;
import lk.ijse.PastryPal.dto.OrderDto;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderDto> {

    boolean placeOrder(OrderDto orderDto) throws SQLException;
}
