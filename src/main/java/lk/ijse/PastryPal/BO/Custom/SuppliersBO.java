package lk.ijse.PastryPal.BO.Custom;

import lk.ijse.PastryPal.BO.SuperBO;
import lk.ijse.PastryPal.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SuppliersBO extends SuperBO {

    String generateNextSupplierID() throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;

    List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;

    SupplierDto searchSupplier(String searchId) throws SQLException, ClassNotFoundException;

    SupplierDto searchSupplierByPhoneNumber(String searchPhoneNumber) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;

    String getTotalSuppliers() throws SQLException, ClassNotFoundException;
}
