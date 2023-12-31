package lk.ijse.PastryPal.DAO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO {

    String generateNextID() throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;

    List<T> getAll() throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    T search(String searchId) throws SQLException, ClassNotFoundException;

    T searchPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    String getTotal() throws SQLException, ClassNotFoundException;
}
