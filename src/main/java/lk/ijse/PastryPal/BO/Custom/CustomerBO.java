package lk.ijse.PastryPal.BO.Custom;

import lk.ijse.PastryPal.BO.SuperBO;
import lk.ijse.PastryPal.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    String generateNextCustomerID() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String searchId) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomerPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean isValidCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    String[] getCustomerByPhoneNumber(String phone) throws SQLException, ClassNotFoundException;

    String[] getCustomerByID(String id) throws SQLException, ClassNotFoundException;

    String getCountOFLoyaltyCustomers() throws SQLException, ClassNotFoundException;

    String getTotalCustomer() throws SQLException, ClassNotFoundException;
}
