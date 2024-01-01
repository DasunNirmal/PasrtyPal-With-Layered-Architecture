package lk.ijse.PastryPal.BO.Custom;

import lk.ijse.PastryPal.BO.SuperBO;
import lk.ijse.PastryPal.dto.ComplainDto;

import java.sql.SQLException;
import java.util.List;

public interface ComplainBO extends SuperBO {

    String generateNextID() throws SQLException, ClassNotFoundException;

    boolean save(ComplainDto dto) throws SQLException, ClassNotFoundException;

    boolean update(ComplainDto dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    List<ComplainDto> getAll() throws SQLException, ClassNotFoundException;

    String getTotal() throws SQLException, ClassNotFoundException;
}
