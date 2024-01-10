package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.DAO.custom.OrderDetailDAO;
import lk.ijse.PastryPal.Entity.OrderDetail;
import lk.ijse.PastryPal.dto.OrderDto;
import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO order_details VALUES (?,?,?,?)",entity.getOrder_id(),
                entity.getProduct_id(),entity.getQty(),entity.getUnit_price());
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String searchId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetail searchPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException {
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
