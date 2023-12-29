package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.dto.ProductDto;
import lk.ijse.PastryPal.dto.tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    String generateNextItemID() throws SQLException, ClassNotFoundException;

    boolean saveProduct(ProductDto dto) throws SQLException, ClassNotFoundException;

    boolean updateProducts(ProductDto dto) throws SQLException, ClassNotFoundException;

    ProductDto searchProductById(String searchId) throws SQLException, ClassNotFoundException;

    ProductDto searchProductByName(String searchName) throws SQLException, ClassNotFoundException;

    boolean deleteProduct(String itemId) throws SQLException, ClassNotFoundException;

    List<ProductDto> getAllProducts() throws SQLException, ClassNotFoundException;

    boolean updateProduct(List<OrderTm> orderTmList) throws SQLException, ClassNotFoundException;

    String[] getProductsByName(String searchTerm) throws SQLException, ClassNotFoundException;

    String getTotalProducts() throws SQLException, ClassNotFoundException;
}
