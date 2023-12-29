package lk.ijse.PastryPal.DAO.Custom;

import lk.ijse.PastryPal.dto.DetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface JoinQueryDAO {
    List<DetailsDto> getAllDetails() throws SQLException, ClassNotFoundException;
}
