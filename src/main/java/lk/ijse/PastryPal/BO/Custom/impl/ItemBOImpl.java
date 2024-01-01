package lk.ijse.PastryPal.BO.Custom.impl;

import lk.ijse.PastryPal.BO.Custom.ItemBO;
import lk.ijse.PastryPal.DAO.DAOFactory;
import lk.ijse.PastryPal.DAO.custom.ItemDAO;
import lk.ijse.PastryPal.Entity.Item;
import lk.ijse.PastryPal.dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEMS);

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNextID();
    }

    @Override
    public boolean save(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItem_id(), dto.getProduct_name(),dto.getQty(),dto.getSupplier_id(),
                dto.getSupplier_name(),dto.getSupplier_phone_number()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public boolean update(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItem_id(), dto.getProduct_name(),dto.getQty(),dto.getSupplier_id(),
                dto.getSupplier_name(),dto.getSupplier_phone_number()));
    }

    @Override
    public List<ItemDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> itemDto = new ArrayList<>();
        List<Item> items = itemDAO.getAll();

        for (Item item : items) {
            itemDto.add(new ItemDto(item.getItem_id(), item.getProduct_name(),item.getQty(),item.getSupplier_id(),
                    item.getSupplier_name(),item.getSupplier_phone_number()));
        }
        return itemDto;
    }

    @Override
    public String getTotal() throws SQLException, ClassNotFoundException {
        return itemDAO.getTotal();
    }
}
