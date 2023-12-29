package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.dto.CustomerDto;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

    String generateNextCustomer() throws SQLException, ClassNotFoundException;

    boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException;

    List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String searchId) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomerByPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException;

    boolean deleteCustomers(String id) throws SQLException, ClassNotFoundException;

    boolean isValidCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;

    String[] getCustomerByPhoneNumber(String phone) throws SQLException, ClassNotFoundException;

    String[] getCustomerByID(String id) throws SQLException, ClassNotFoundException;

    String getCountOFLotalty() throws SQLException, ClassNotFoundException;

    String getTotalCustomers() throws SQLException, ClassNotFoundException;
}
