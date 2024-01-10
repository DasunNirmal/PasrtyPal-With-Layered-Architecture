package lk.ijse.PastryPal.BO.Custom.impl;

import lk.ijse.PastryPal.BO.Custom.OrderBO;
import lk.ijse.PastryPal.DAO.DAOFactory;
import lk.ijse.PastryPal.DAO.custom.OrderDAO;
import lk.ijse.PastryPal.DAO.custom.OrderDetailDAO;
import lk.ijse.PastryPal.DAO.custom.ProductDAO;
import lk.ijse.PastryPal.DB.DbConnection;
import lk.ijse.PastryPal.Entity.Order;
import lk.ijse.PastryPal.Entity.OrderDetail;
import lk.ijse.PastryPal.Entity.Product;
import lk.ijse.PastryPal.dto.OrderDetailDto;
import lk.ijse.PastryPal.dto.OrderDto;
import lk.ijse.PastryPal.dto.ProductDto;
import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCTS);

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextID();
    }

    @Override
    public boolean placeOrder(OrderDto orderDto, List<OrderTm> orderDetailDto) throws SQLException {
        String orderId = orderDto.getOrder_id();
        LocalDate date = orderDto.getDate();
        String customerId = orderDto.getCustomer_id();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderDAO.save(new Order(orderId,date,customerId));
            if (isOrderSaved){
                boolean isUpdated = productDAO.updateProduct(orderDto.getOrderTmList());
                if (isUpdated){
                    for (OrderTm dto : orderDetailDto) {
                        boolean isOrderDetailSaved = orderDetailDAO.save(new OrderDetail(orderId,dto.getProduct_id(),dto.getQty(),dto.getUnit_price()));
                        if (isOrderDetailSaved){
                            connection.commit();
                        }
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
}
