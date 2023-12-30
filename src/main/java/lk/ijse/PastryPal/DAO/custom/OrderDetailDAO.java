package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO {

    boolean save(String orderId, List<OrderTm> orderTmList) throws SQLException, ClassNotFoundException;
}
