package lk.ijse.PastryPal.BO.Custom;

import lk.ijse.PastryPal.BO.SuperBO;
import lk.ijse.PastryPal.dto.ComplainDto;

import java.sql.SQLException;
import java.util.List;

public interface ComplainBO extends SuperBO {

    String generateNextComplainID() throws SQLException, ClassNotFoundException;

    boolean saveComplain(ComplainDto dto) throws SQLException, ClassNotFoundException;

    boolean updateComplain(ComplainDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteComplain(String id) throws SQLException, ClassNotFoundException;

    List<ComplainDto> getAllComplains() throws SQLException, ClassNotFoundException;

    String getTotalComplains() throws SQLException, ClassNotFoundException;
}
