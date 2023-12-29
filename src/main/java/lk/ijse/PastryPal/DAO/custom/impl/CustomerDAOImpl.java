package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.custom.CustomerDAO;
import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.dto.CustomerDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private String splitCustomerID(String currentCustomerID) {
        if (currentCustomerID != null) {
            String[] split = currentCustomerID.split("[C]");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("C%03d", id);
        } else {
            return "C001";
        }
    }

    @Override
    public String generateNextCustomer() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");
        if (resultSet.next()){
            return splitCustomerID(resultSet.getString(1));
        }
        return splitCustomerID(null);
    }

    @Override
    public boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES (?,?,?,?)",dto.getCustomer_id(),dto.getName(),
                dto.getAddress(),dto.getPhone_number());
    }

    @Override
    public List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");

        ArrayList<CustomerDto> dtoList = new ArrayList<>();
        while (resultSet.next()){
            if (resultSet.getString(2) != null
                    || resultSet.getString(3) != null
                    || resultSet.getString(4) != null) {
                dtoList.add(
                        new CustomerDto(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)));
            }
        }
        return dtoList;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name = ?,address = ?,phone_number = ? WHERE customer_id =?",
                dto.getName(),dto.getAddress(),dto.getPhone_number(),dto.getCustomer_id());
    }

    @Override
    public CustomerDto searchCustomer(String searchId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customer_id = ?",searchId);

        CustomerDto dto = null;
        if (resultSet.next()){
            String customer_id = resultSet.getString(1);
            String customer_name = resultSet.getString(2);
            String customer_address = resultSet.getString(3);
            String customer_phoneNumber = resultSet.getString(4);

            dto = new CustomerDto(customer_id,customer_name,customer_address,customer_phoneNumber);
        }
        return dto;
    }

    @Override
    public CustomerDto searchCustomerByPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE phone_number = ?",phoneNumber);

        CustomerDto dto = null;
        if (resultSet.next()) {
            String customer_id = resultSet.getString(1);
            String customer_name = resultSet.getString(2);
            String customer_address = resultSet.getString(3);
            String customer_phoneNumber = resultSet.getString(4);

            dto = new CustomerDto(customer_id, customer_name, customer_address, customer_phoneNumber);
        }
        return dto;
    }

    @Override
    public boolean deleteCustomers(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE customer_id = ?",id);
    }

    @Override
    public boolean isValidCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customer_id = ?",dto.getCustomer_id());
        return resultSet.next();
    }

    @Override
    public String[] getCustomerByPhoneNumber(String phone) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE phone_number LIKE ?","%"+phone+"%");

        List<String> customerPhoneNumbers = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString(4);
            customerPhoneNumbers.add(name);
        }
        return customerPhoneNumbers.toArray(new String[0]);
    }

    @Override
    public String[] getCustomerByID(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customer_id LIKE ?","%" +id+ "%");

        List<String> customerPhoneNumbers = new ArrayList<>();
        while (resultSet.next()) {
            String ID = resultSet.getString(1);
            customerPhoneNumbers.add(ID);
        }
        return customerPhoneNumbers.toArray(new String[0]);
    }

    @Override
    public String getCountOFLotalty() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM customer WHERE name IS NOT NULL;");
        resultSet.next();
        return resultSet.getString(1);
    }

    @Override
    public String getTotalCustomers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) FROM customer");
        resultSet.next();
        return resultSet.getString(1);
    }
}
