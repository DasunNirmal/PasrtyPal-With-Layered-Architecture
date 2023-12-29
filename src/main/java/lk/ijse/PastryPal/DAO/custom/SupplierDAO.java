package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO {

    String generateNextSupplierID() throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;

    List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;

    SupplierDto searchSupplierById(String searchId) throws SQLException, ClassNotFoundException;

    SupplierDto searchSupplierByPhoneNumber(String searchPhoneNumber) throws SQLException, ClassNotFoundException;

    boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException;

    boolean updateSuppliers(SupplierDto dto) throws SQLException, ClassNotFoundException;

    String getTotalSuppliers() throws SQLException, ClassNotFoundException;
}
