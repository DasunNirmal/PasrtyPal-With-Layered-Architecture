package lk.ijse.PastryPal.BO.Custom.impl;

import lk.ijse.PastryPal.BO.Custom.SuppliersBO;
import lk.ijse.PastryPal.DAO.DAOFactory;
import lk.ijse.PastryPal.DAO.custom.SupplierDAO;
import lk.ijse.PastryPal.Entity.Supplier;
import lk.ijse.PastryPal.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersBOImpl implements SuppliersBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIERS);

    @Override
    public String generateNextSupplierID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNextID();
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getSupplier_id(),dto.getName(),dto.getDate(),dto.getPhone_number()));
    }

    @Override
    public List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDto> supplierDto = new ArrayList<>();
        List<Supplier> suppliers = supplierDAO.getAll();

        for (Supplier supplier : suppliers) {
            supplierDto.add(new SupplierDto(supplier.getSupplier_id(),supplier.getName(),supplier.getDate(), supplier.getPhone_number()));
        }
        return supplierDto;
    }

    @Override
    public SupplierDto searchSupplier(String searchId) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(searchId);
        if (supplier != null) {
            return new SupplierDto(supplier.getSupplier_id(),supplier.getName(),supplier.getDate(), supplier.getPhone_number());
        } else {
            return null;
        }
    }

    @Override
    public SupplierDto searchSupplierByPhoneNumber(String searchPhoneNumber) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(searchPhoneNumber);
        if (supplier != null) {
            return new SupplierDto(supplier.getSupplier_id(),supplier.getName(),supplier.getDate(), supplier.getPhone_number());
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSupplier_id(),dto.getName(),dto.getDate(),dto.getPhone_number()));
    }

    @Override
    public String getTotalSuppliers() throws SQLException, ClassNotFoundException {
        return supplierDAO.getTotal();
    }
}
