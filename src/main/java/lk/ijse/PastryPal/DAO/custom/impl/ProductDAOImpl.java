package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.DAO.custom.ProductDAO;
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

    public String generateNextItemID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT product_id FROM products ORDER BY product_id DESC LIMIT 1");
        if (resultSet.next()){
            return splitItemID(resultSet.getString(1));
        }
        return splitItemID(null);
    }

    public boolean saveProduct(ProductDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO products VALUES (?,?,?,?)",dto.getProduct_id(),dto.getDescription(),
                dto.getQty(),dto.getPrice());
    }

    public boolean updateProducts(ProductDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE products SET description = ?, qty = ?, price = ? WHERE product_id = ?",
                dto.getDescription(),dto.getQty(),dto.getPrice(),dto.getProduct_id());
    }

    public ProductDto searchProductById(String searchId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM products WHERE product_id = ?",searchId);

        ProductDto dto = null;
        if (resultSet.next()){
            String Product_id = resultSet.getString(1);
            String Product_description = resultSet.getString(2);
            int Product_qty = Integer.parseInt(resultSet.getString(3));
            double Product_price = Double.parseDouble(resultSet.getString(4));

            dto = new ProductDto(Product_id, Product_description, Product_qty ,Product_price);
        }
        return dto;
    }

    public ProductDto searchProductByName(String searchName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM products WHERE description = ?",searchName);

        ProductDto dto = null;
        if (resultSet.next()){
            String Product_Id = resultSet.getString(1);
            String Product_description = resultSet.getString(2);
            int Product_qty = Integer.parseInt(resultSet.getString(3));
            double Product_price = Double.parseDouble(resultSet.getString(4));

            dto = new ProductDto(Product_Id, Product_description, Product_qty, Product_price);
        }
        return  dto;
    }

    public boolean deleteProduct(String itemId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM products WHERE product_id = ?",itemId);
    }

    public List<ProductDto> getAllProducts() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM products");

        ArrayList<ProductDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new ProductDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return dtoList;
    }

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
    public String getTotalProducts() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) FROM products");
        resultSet.next();
        return resultSet.getString(1);
    }
}