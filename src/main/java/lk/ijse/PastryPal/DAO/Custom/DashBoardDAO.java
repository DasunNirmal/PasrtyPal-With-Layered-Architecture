package lk.ijse.PastryPal.DAO.Custom;

import java.sql.SQLException;

public interface DashBoardDAO {
    String getTotalSales() throws SQLException, ClassNotFoundException;
}
