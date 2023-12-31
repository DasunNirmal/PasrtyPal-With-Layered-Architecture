package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.DAO.custom.OrderDetailDAO;
import lk.ijse.PastryPal.dto.OrderDto;
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

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDto search(String searchId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDto searchPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getTotal() throws SQLException, ClassNotFoundException {
        return null;
    }
}
