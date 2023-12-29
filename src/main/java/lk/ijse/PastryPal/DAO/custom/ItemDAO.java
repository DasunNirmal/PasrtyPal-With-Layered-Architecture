package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    String generateNextItemID() throws SQLException, ClassNotFoundException;

    boolean saveItems(ItemDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteItems(String id) throws SQLException, ClassNotFoundException;

    boolean updateItems(ItemDto dto) throws SQLException, ClassNotFoundException;

    List<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;

    String getTotalItems() throws SQLException, ClassNotFoundException;
}
