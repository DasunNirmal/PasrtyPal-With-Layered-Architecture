package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.DAO.CrudDAO;
import lk.ijse.PastryPal.dto.ProductDto;
import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO<ProductDto> {

    ProductDto searchProductByName(String searchName) throws SQLException, ClassNotFoundException;

    boolean updateProduct(List<OrderTm> orderTmList) throws SQLException, ClassNotFoundException;

    String[] getProductsByName(String searchTerm) throws SQLException, ClassNotFoundException;
}
