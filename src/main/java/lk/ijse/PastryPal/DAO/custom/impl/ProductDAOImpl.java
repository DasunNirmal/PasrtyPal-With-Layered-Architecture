package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.DAO.custom.ProductDAO;
import lk.ijse.PastryPal.Entity.Product;
import lk.ijse.PastryPal.dto.ProductDto;
import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private String splitItemID(String currentItemID){
        if (currentItemID != null){
            String [] split = currentItemID.split("[P]");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("P%03d",id);
        }else {
            return "P001";
        }
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT product_id FROM products ORDER BY product_id DESC LIMIT 1");
        if (resultSet.next()){
            return splitItemID(resultSet.getString(1));
        }
        return splitItemID(null);
    }

    @Override
    public boolean save(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO products VALUES (?,?,?,?)",entity.getProduct_id(),entity.getDescription(),
                entity.getQty(),entity.getPrice());
    }

    @Override
    public boolean update(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE products SET description = ?, qty = ?, price = ? WHERE product_id = ?",
                entity.getDescription(),entity.getQty(),entity.getPrice(),entity.getProduct_id());
    }

    @Override
    public Product search(String searchId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM products WHERE product_id = ?",searchId);

        Product entity = null;
        if (resultSet.next()){
            String Product_id = resultSet.getString(1);
            String Product_description = resultSet.getString(2);
            int Product_qty = Integer.parseInt(resultSet.getString(3));
            double Product_price = Double.parseDouble(resultSet.getString(4));

            entity = new Product(Product_id, Product_description, Product_qty ,Product_price);
        }
        return entity;
    }

    @Override
    public Product searchPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Product searchProductByName(String searchName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM products WHERE description = ?",searchName);

        Product entity = null;
        if (resultSet.next()){
            String Product_Id = resultSet.getString(1);
            String Product_description = resultSet.getString(2);
            int Product_qty = Integer.parseInt(resultSet.getString(3));
            double Product_price = Double.parseDouble(resultSet.getString(4));

            entity = new Product(Product_Id, Product_description, Product_qty, Product_price);
        }
        return  entity;
    }

    @Override
    public boolean delete(String itemId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM products WHERE product_id = ?",itemId);
    }

    @Override
    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM products");

        ArrayList<Product> entityList = new ArrayList<>();

        while (resultSet.next()){
            entityList.add(
                    new Product(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return entityList;
    }

    @Override
    public boolean updateProduct(List<OrderTm> orderTmList) throws SQLException, ClassNotFoundException {
        for (OrderTm tm: orderTmList){
            if (!updateQty(tm.getProduct_id(),tm.getQty())){
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String productId, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE products SET qty = qty - ? WHERE product_id = ?",qty,productId);
    }

    @Override
    public String[] getProductsByName(String searchTerm) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM products WHERE description LIKE ?",
                "%" + searchTerm + "%");

        List<String> productNames = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("description");
            productNames.add(name);
        }
        return productNames.toArray(new String[0]);
    }

    @Override
    public String getTotal() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) FROM products");
        resultSet.next();
        return resultSet.getString(1);
    }
}
