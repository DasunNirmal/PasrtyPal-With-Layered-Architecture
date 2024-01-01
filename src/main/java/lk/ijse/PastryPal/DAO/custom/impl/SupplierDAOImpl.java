package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.custom.SupplierDAO;
import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.Entity.Supplier;

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

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_id FROM suppliers ORDER BY supplier_id DESC LIMIT 1");
        if (resultSet.next()){
            return splitSupplierID(resultSet.getString(1));
        }
        return splitSupplierID(null);
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO suppliers VALUES (?,?,?,?)",entity.getSupplier_id(),entity.getName(),
                entity.getDate(),entity.getPhone_number());
    }

    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM suppliers");

        ArrayList<Supplier> entityList = new ArrayList<>();

        while (resultSet.next()){
            entityList.add(
                    new Supplier(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDate(3).toLocalDate(),
                            resultSet.getString(4)
                    )
            );
        }
        return entityList;
    }

    @Override
    public Supplier search(String searchId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM suppliers WHERE supplier_id = ?",searchId);

        Supplier entity = null;
        if (resultSet.next()) {
            String supplier_id = resultSet.getString(1);
            String supplier_name = resultSet.getString(2);
            LocalDate supplier_date = LocalDate.parse(resultSet.getString(3));
            String supplier_phone_number = resultSet.getString(4);

            entity = new Supplier(supplier_id ,supplier_name, supplier_date ,supplier_phone_number);
        }
        return entity;
    }

    @Override
    public Supplier searchPhoneNumber(String searchPhoneNumber) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM suppliers WHERE phone_number = ?",searchPhoneNumber);

        Supplier entity = null;
        if (resultSet.next()){
            String supplier_id = resultSet.getString(1);
            String supplier_name = resultSet.getString(2);
            LocalDate supplier_date = LocalDate.parse(resultSet.getString(3));
            String supplier_phone_number = resultSet.getString(4);

            entity = new Supplier(supplier_id, supplier_name, supplier_date, supplier_phone_number);
        }
        return entity;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM suppliers WHERE supplier_id = ?",id);
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE suppliers SET name = ?,s_date = ?,phone_number = ? WHERE supplier_id = ?",
                entity.getName(),entity.getDate(),entity.getPhone_number(),entity.getSupplier_id());
    }

    @Override
    public String getTotal() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) FROM suppliers");
        resultSet.next();
        return resultSet.getString(1);
    }
}
