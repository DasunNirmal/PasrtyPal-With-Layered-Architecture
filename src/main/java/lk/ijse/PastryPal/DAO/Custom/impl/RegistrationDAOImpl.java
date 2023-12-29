package lk.ijse.PastryPal.DAO.Custom.impl;

import lk.ijse.PastryPal.DAO.Custom.RegistrationDAO;
import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.dto.RegistrationDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDAOImpl implements RegistrationDAO {

    public boolean saveUser(RegistrationDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO users VALUES (?,?)",dto.getUser_name(),dto.getPassword());
    }

    public boolean isValidUser(String userName, String pw) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM users WHERE user_name = ? AND password = ?",userName,pw);
        return resultSet.next();
    }

    public RegistrationDto getUserInfo(String userName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM users WHERE user_name = ?",userName);
        if (resultSet.next()) {
            String retrievedUserName = resultSet.getString("user_name");
            String retrievedPassword = resultSet.getString("password");

            return new RegistrationDto(retrievedUserName, retrievedPassword);
        }
        return null; // User isn't found
    }

    public boolean check(String userName, String pw) throws SQLException, ClassNotFoundException {
        return isValidUser(userName,pw);
    }
}
