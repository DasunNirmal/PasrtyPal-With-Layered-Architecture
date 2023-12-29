package lk.ijse.PastryPal.DAO.custom;

import java.sql.SQLException;

public interface DashBoardDAO {
    String getTotalSales() throws SQLException, ClassNotFoundException;
}
