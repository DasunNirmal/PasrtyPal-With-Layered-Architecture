package lk.ijse.PastryPal.BO.Custom;

import lk.ijse.PastryPal.BO.SuperBO;
import lk.ijse.PastryPal.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    String generateNextEmployeeID() throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    List<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;

    String getTotalEmployees() throws SQLException, ClassNotFoundException;
}
