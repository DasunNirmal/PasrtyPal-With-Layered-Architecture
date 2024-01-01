package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.DAO.CrudDAO;
import lk.ijse.PastryPal.Entity.Customer;
import lk.ijse.PastryPal.dto.CustomerDto;
import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {

    boolean isValidCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    String[] getCustomerByPhoneNumber(String phone) throws SQLException, ClassNotFoundException;

    String[] getCustomerByID(String id) throws SQLException, ClassNotFoundException;

    String getCountOFLoyalty() throws SQLException, ClassNotFoundException;

}
