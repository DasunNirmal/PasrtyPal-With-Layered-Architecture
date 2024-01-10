package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.DAO.CrudDAO;
import lk.ijse.PastryPal.Entity.Product;
import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {

    Product searchProductByName(String searchName) throws SQLException, ClassNotFoundException;

    String[] getProductsByName(String searchTerm) throws SQLException, ClassNotFoundException;

    boolean updateQTY(Product entity) throws SQLException, ClassNotFoundException;
}
