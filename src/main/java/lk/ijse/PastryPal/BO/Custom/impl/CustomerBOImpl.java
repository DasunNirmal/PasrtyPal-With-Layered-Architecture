package lk.ijse.PastryPal.BO.Custom.impl;

import lk.ijse.PastryPal.BO.Custom.CustomerBO;
import lk.ijse.PastryPal.DAO.DAOFactory;
import lk.ijse.PastryPal.DAO.custom.CustomerDAO;
import lk.ijse.PastryPal.Entity.Customer;
import lk.ijse.PastryPal.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public String generateNextCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNextID();
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCustomer_id(),dto.getName(),dto.getAddress(),dto.getPhone_number()));
    }

    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> customerDto = new ArrayList<>();
        List<Customer> customers = customerDAO.getAll();

        for (Customer customer : customers) {
            customerDto.add(new CustomerDto(customer.getCustomer_id(),customer.getName(),customer.getAddress(),customer.getPhone_number()));
        }
        return customerDto;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCustomer_id(),dto.getName(),dto.getAddress(),dto.getPhone_number()));
    }

    @Override
    public CustomerDto searchCustomer(String searchId) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(searchId);
        if (customer != null) {
            return new CustomerDto(customer.getCustomer_id(),customer.getName(),customer.getAddress(),customer.getPhone_number());
        } else {
            return null;
        }
    }

    @Override
    public CustomerDto searchCustomerPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchPhoneNumber(phoneNumber);
        if (customer != null) {
            return new CustomerDto(customer.getCustomer_id(),customer.getName(),customer.getAddress(),customer.getPhone_number());
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean isValidCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.isValidCustomer(new Customer(dto.getCustomer_id(),dto.getName(),dto.getAddress(),dto.getPhone_number()));
    }

    @Override
    public String[] getCustomerByPhoneNumber(String phone) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerByPhoneNumber(phone);
    }

    @Override
    public String[] getCustomerByID(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerByID(id);
    }

    @Override
    public String getCountOFLoyaltyCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getCountOFLoyalty();
    }

    @Override
    public String getTotalCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getTotal();
    }
}
