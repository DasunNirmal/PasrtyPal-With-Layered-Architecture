package lk.ijse.PastryPal.DAO.custom;

import lk.ijse.PastryPal.dto.DetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface JoinQueryDAO {
    List<DetailsDto> getAllDetails() throws SQLException, ClassNotFoundException;
}
