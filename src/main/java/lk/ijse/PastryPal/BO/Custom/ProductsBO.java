package lk.ijse.PastryPal.BO.Custom;

import lk.ijse.PastryPal.BO.SuperBO;
import lk.ijse.PastryPal.Entity.Product;
import lk.ijse.PastryPal.dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductsBO extends SuperBO {

    String generateNextProductID() throws SQLException, ClassNotFoundException;

    boolean saveProduct(ProductDto dto) throws SQLException, ClassNotFoundException;

    boolean updateProduct(ProductDto dto) throws SQLException, ClassNotFoundException;

    ProductDto searchProduct(String searchId) throws SQLException, ClassNotFoundException;

    ProductDto searchProductByName(String searchName) throws SQLException, ClassNotFoundException;

    boolean deleteProduct(String itemId) throws SQLException, ClassNotFoundException;

    List<ProductDto> getAllProducts() throws SQLException, ClassNotFoundException;

    String[] getProductsByName(String searchTerm) throws SQLException, ClassNotFoundException;

    String getTotalProducts() throws SQLException, ClassNotFoundException;

}
