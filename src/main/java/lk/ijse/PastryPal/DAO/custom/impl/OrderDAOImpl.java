package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.DAO.custom.OrderDAO;
import lk.ijse.PastryPal.DAO.custom.OrderDetailDAO;
import lk.ijse.PastryPal.DAO.custom.ProductDAO;
import lk.ijse.PastryPal.DB.DbConnection;
import lk.ijse.PastryPal.dto.OrderDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    ProductDAO productDAO = new ProductDAOImpl();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

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

    public boolean placeOrder(OrderDto orderDto) throws SQLException {
        String orderId = orderDto.getOrder_id();
        LocalDate date = orderDto.getDate();
        String customerId = orderDto.getCustomer_id();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = save(orderId,date,customerId);
            if (isOrderSaved){
                boolean isUpdated = productDAO.updateProduct(orderDto.getOrderTmList());
                if (isUpdated){
                    boolean isOrderDetailSaved = orderDetailDAO.save(orderDto.getOrder_id(),orderDto.getOrderTmList());
                    if (isOrderDetailSaved){
                        connection.commit();
                    }
                }
            }
            connection.rollback();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    private boolean save(String orderId, LocalDate date, String customerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES (?,?,?)",orderId,date,customerId);
    }
}
