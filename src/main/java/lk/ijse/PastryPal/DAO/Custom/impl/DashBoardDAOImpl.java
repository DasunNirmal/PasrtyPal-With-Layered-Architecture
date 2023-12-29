package lk.ijse.PastryPal.DAO.Custom.impl;

import lk.ijse.PastryPal.DAO.Custom.DashBoardDAO;
import lk.ijse.PastryPal.DAO.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardDAOImpl implements DashBoardDAO {
    @Override
    public String getTotalSales() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(unit_price) AS total_sum FROM order_details;");
        resultSet.next();
        return resultSet.getString(1);
    }
}
