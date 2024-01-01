package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.custom.ItemDAO;
import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.Entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private String splitItemID(String currentItemID){
        if (currentItemID != null){
            String [] split = currentItemID.split("[I]");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("I%03d",id);
        }else {
            return "I001";
        }
    }
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT item_id FROM items ORDER BY item_id DESC LIMIT 1");
        if (resultSet.next()){
            return splitItemID(resultSet.getString(1));
        }
        return splitItemID(null);
    }

    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO items VALUES (?,?,?,?,?,?)",entity.getItem_id(),entity.getProduct_name(),
                entity.getQty(),entity.getSupplier_id(),entity.getSupplier_name(),entity.getSupplier_phone_number());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM items WHERE item_id = ?",id);
    }

    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE items SET product_name = ?, qty = ?, supplier_id = ?, supplier_name = ?, supplier_phone_number = ? WHERE item_id = ?",
                entity.getProduct_name(),entity.getQty(),entity.getSupplier_id(),entity.getProduct_name(),entity.getSupplier_phone_number(),
                entity.getItem_id());
    }

    @Override
    public Item search(String searchId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Item searchPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException {
        return null;
    }

    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM items");

        ArrayList<Item> entityList = new ArrayList<>();

        while (resultSet.next()){
            entityList.add(
                    new Item(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return entityList;
    }

    @Override
    public String getTotal() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) FROM items");
        resultSet.next();
        return resultSet.getString(1);
    }
}
