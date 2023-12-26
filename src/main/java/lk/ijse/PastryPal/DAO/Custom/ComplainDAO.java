package lk.ijse.PastryPal.DAO.Custom;

import lk.ijse.PastryPal.dto.ComplainDto;
import java.sql.SQLException;
import java.util.List;

public interface ComplainDAO {

    String generateNextComplainID() throws SQLException, ClassNotFoundException;

    boolean saveComplain(ComplainDto dto) throws SQLException, ClassNotFoundException;

    boolean updateComplains(ComplainDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteComplains(String id) throws SQLException, ClassNotFoundException;

    List<ComplainDto> getAllComplains() throws SQLException, ClassNotFoundException;

    String getAllCount() throws SQLException, ClassNotFoundException;
}
