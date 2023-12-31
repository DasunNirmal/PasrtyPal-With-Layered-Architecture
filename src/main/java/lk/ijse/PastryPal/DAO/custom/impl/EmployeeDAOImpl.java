package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.custom.EmployeeDAO;
import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.Entity.Employee;
import lk.ijse.PastryPal.dto.EmployeeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private String splitEmployeeID(String currentEmployeeID){
        if (currentEmployeeID != null){
            String [] split = currentEmployeeID.split("[E]");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("E%03d",id);
        }else {
            return "E001";
        }
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT employee_id FROM employee ORDER BY employee_id DESC LIMIT 1");
        if (resultSet.next()){
            return splitEmployeeID(resultSet.getString(1));
        }
        return splitEmployeeID(null);
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?)",entity.getEmployee_id(),entity.getFirst_name(),
                entity.getLast_name(),entity.getAddress(),entity.getPhone_number());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET first_name = ?,last_name = ?,address = ?,phone_number = ? WHERE employee_id = ?",
                entity.getFirst_name(),entity.getLast_name(),entity.getAddress(),entity.getPhone_number(),entity.getEmployee_id());
    }

    @Override
    public Employee search(String searchId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Employee searchPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE employee_id = ?",id);
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");

        ArrayList<Employee> entityList = new ArrayList<>();

        while (resultSet.next()){
            entityList.add(
                    new Employee(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return entityList;
    }

    @Override
    public String getTotal() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) FROM employee");
        resultSet.next();
        return resultSet.getString(1);
    }
}
