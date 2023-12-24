package lk.ijse.PastryPal.DAO.Custom;

import lk.ijse.PastryPal.dto.ComplainDto;
import java.sql.SQLException;
import java.util.List;

public interface ComplainDAO {

    String generateNextComplainID() throws SQLException;

    boolean saveComplain(ComplainDto dto) throws SQLException;

    boolean updateComplains(ComplainDto dto) throws SQLException;

    boolean deleteComplains(String id) throws SQLException;

    List<ComplainDto> getAllComplains() throws SQLException;

}
