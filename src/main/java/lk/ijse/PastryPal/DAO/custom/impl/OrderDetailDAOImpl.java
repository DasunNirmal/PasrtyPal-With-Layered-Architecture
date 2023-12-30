package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.DAO.custom.OrderDetailDAO;
import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public boolean save(String orderId, List<OrderTm> orderTmList) throws SQLException, ClassNotFoundException {
        for (OrderTm tm : orderTmList){
            if (!saveOrderDetails(orderId, tm)){
                return false;
            }
        }
        return true;
    }

    private boolean saveOrderDetails(String orderId,OrderTm orderTm) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO order_details VALUES (?,?,?,?)",orderId,orderTm.getProduct_id(),
                orderTm.getQty(),orderTm.getUnit_price());
    }
}
