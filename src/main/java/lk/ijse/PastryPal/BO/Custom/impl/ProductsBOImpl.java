package lk.ijse.PastryPal.BO.Custom.impl;

import lk.ijse.PastryPal.BO.Custom.ProductsBO;
import lk.ijse.PastryPal.DAO.DAOFactory;
import lk.ijse.PastryPal.DAO.custom.ProductDAO;
import lk.ijse.PastryPal.Entity.Product;
import lk.ijse.PastryPal.dto.ProductDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsBOImpl implements ProductsBO {
    
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCTS);
    
    @Override
    public String generateNextProductID() throws SQLException, ClassNotFoundException {
        return productDAO.generateNextID();
    }

    @Override
    public boolean saveProduct(ProductDto dto) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Product(dto.getProduct_id(), dto.getDescription(), dto.getQty(), dto.getPrice()));
    }

    @Override
    public boolean updateProduct(ProductDto dto) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(dto.getProduct_id(), dto.getDescription(), dto.getQty(), dto.getPrice()));
    }

    @Override
    public ProductDto searchProduct(String searchId) throws SQLException, ClassNotFoundException {
        Product product = productDAO.search(searchId);
        if (product != null) {
            return new ProductDto(product.getProduct_id(),product.getDescription(),product.getQty(),product.getPrice());
        } else {
            return null;
        }
    }

    @Override
    public ProductDto searchProductByName(String searchName) throws SQLException, ClassNotFoundException {
        Product product = productDAO.searchProductByName(searchName);
        if (product != null) {
            return new ProductDto(product.getProduct_id(),product.getDescription(),product.getQty(),product.getPrice());
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteProduct(String itemId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(itemId);
    }

    @Override
    public List<ProductDto> getAllProducts() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDto> productDto = new ArrayList<>();
        List<Product> products = productDAO.getAll();

        for (Product product : products) {
            productDto.add(new ProductDto(product.getProduct_id(),product.getDescription(),product.getQty(),product.getPrice()));
        }
        return productDto;
    }

    @Override
    public String[] getProductsByName(String searchTerm) throws SQLException, ClassNotFoundException {
        return productDAO.getProductsByName(searchTerm);
    }

    @Override
    public String getTotalProducts() throws SQLException, ClassNotFoundException {
        return productDAO.getTotal();
    }
}
