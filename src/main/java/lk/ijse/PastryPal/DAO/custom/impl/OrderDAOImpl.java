package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.DAO.custom.OrderDAO;
import lk.ijse.PastryPal.DAO.custom.OrderDetailDAO;
import lk.ijse.PastryPal.DAO.custom.ProductDAO;
import lk.ijse.PastryPal.DB.DbConnection;
import lk.ijse.PastryPal.Entity.Order;
import lk.ijse.PastryPal.dto.OrderDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private String splitOrderID(String currentOrderID){
        if (currentOrderID != null){
            String [] split = currentOrderID.split("[O]");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("O%03d",id);
        }else {
            return "O001";
        }
    }

    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        if (resultSet.next()){
            return splitOrderID(resultSet.getString(1));
        }
        return splitOrderID(null);
    }

    @Override
    public boolean save(Order dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES (?,?,?)",dto.getOrder_id(),dto.getDate(),dto.getCustomer_id());
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String searchId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Order searchPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getTotal() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) FROM orders");
        resultSet.next();
        return resultSet.getString(1);
    }

    @Override
    public String getTotalOrdersByDate() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM orders WHERE DATE(order_date) = CURDATE();");
        resultSet.next();
        return resultSet.getString(1);
    }
}
