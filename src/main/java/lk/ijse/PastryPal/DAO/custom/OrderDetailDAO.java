package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.DAO.CrudDAO;
import lk.ijse.PastryPal.dto.OrderDto;
import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDto> {

    boolean save(String orderId, List<OrderTm> orderTmList) throws SQLException, ClassNotFoundException;
}
