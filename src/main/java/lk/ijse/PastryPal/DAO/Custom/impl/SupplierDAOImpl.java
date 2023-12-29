package lk.ijse.PastryPal.DAO.Custom.impl;

import lk.ijse.PastryPal.DAO.Custom.SupplierDAO;
import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.dto.SupplierDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    private String splitSupplierID(String currentSupplierID) {
        if (currentSupplierID != null){
            String [] split = currentSupplierID.split("[S]");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("S%03d",id);
        }else {
            return "S001";
        }
    }

    public String generateNextSupplierID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_id FROM suppliers ORDER BY supplier_id DESC LIMIT 1");
        if (resultSet.next()){
            return splitSupplierID(resultSet.getString(1));
        }
        return splitSupplierID(null);
    }

    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO suppliers VALUES (?,?,?,?)",dto.getSupplier_id(),dto.getName(),
                dto.getDate(),dto.getPhone_number());
    }

    public List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM suppliers");

        ArrayList<SupplierDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new SupplierDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDate(3).toLocalDate(),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }

    public SupplierDto searchSupplierById(String searchId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM suppliers WHERE supplier_id = ?",searchId);

        SupplierDto dto = null;
        if (resultSet.next()) {
            String supplier_id = resultSet.getString(1);
            String supplier_name = resultSet.getString(2);
            LocalDate supplier_date = LocalDate.parse(resultSet.getString(3));
            String supplier_phone_number = resultSet.getString(4);

            dto = new SupplierDto(supplier_id ,supplier_name, supplier_date ,supplier_phone_number);
        }
        return dto;
    }

    public SupplierDto searchSupplierByPhoneNumber(String searchPhoneNumber) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM suppliers WHERE phone_number = ?",searchPhoneNumber);

        SupplierDto dto = null;
        if (resultSet.next()){
            String supplier_id = resultSet.getString(1);
            String supplier_name = resultSet.getString(2);
            LocalDate supplier_date = LocalDate.parse(resultSet.getString(3));
            String supplier_phone_number = resultSet.getString(4);

            dto = new SupplierDto(supplier_id, supplier_name, supplier_date, supplier_phone_number);
        }
        return dto;
    }

    public boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM suppliers WHERE supplier_id = ?",id);
    }

    public boolean updateSuppliers(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE suppliers SET name = ?,s_date = ?,phone_number = ? WHERE supplier_id = ?",
                dto.getName(),dto.getDate(),dto.getPhone_number(),dto.getSupplier_id());
    }

    @Override
    public String getTotalSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) FROM suppliers");
        resultSet.next();
        return resultSet.getString(1);
    }
}
