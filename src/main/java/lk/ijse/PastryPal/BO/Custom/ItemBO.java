package lk.ijse.PastryPal.BO.Custom;

import lk.ijse.PastryPal.BO.SuperBO;
import lk.ijse.PastryPal.dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {

    String generateNextID() throws SQLException, ClassNotFoundException;

    boolean save(ItemDto dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(ItemDto dto) throws SQLException, ClassNotFoundException;

    List<ItemDto> getAll() throws SQLException, ClassNotFoundException;

    String getTotal() throws SQLException, ClassNotFoundException;
}
