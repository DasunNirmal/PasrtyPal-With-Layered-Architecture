package lk.ijse.PastryPal.DAO;

import lk.ijse.PastryPal.DB.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {

    public static <T> T execute(String sql, Object... ob) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < ob.length; i++) {
            pstm.setObject((1+i),ob[i]);
        }

        if (sql.startsWith("SELECT")) {
            return (T) pstm.executeQuery();
        } else {
            return (T)(Boolean) (pstm.executeUpdate() > 0);
        }
    }
}
