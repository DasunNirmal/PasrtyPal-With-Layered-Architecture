package lk.ijse.PastryPal.BO.Custom.impl;

import lk.ijse.PastryPal.BO.Custom.EmployeeBO;
import lk.ijse.PastryPal.DAO.DAOFactory;
import lk.ijse.PastryPal.DAO.custom.EmployeeDAO;
import lk.ijse.PastryPal.Entity.Employee;
import lk.ijse.PastryPal.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public String generateNextEmployeeID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNextID();
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getEmployee_id(),dto.getFirst_name(),dto.getLast_name(),dto.getAddress(),dto.getPhone_number()));
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmployee_id(),dto.getFirst_name(),dto.getLast_name(),dto.getAddress(),dto.getPhone_number()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employeeDto = new ArrayList<>();
        List<Employee> employees = employeeDAO.getAll();

        for (Employee employee : employees) {
            employeeDto.add(new EmployeeDto(employee.getEmployee_id(),employee.getFirst_name(),employee.getLast_name(),employee.getAddress(),employee.getPhone_number()));
        }
        return employeeDto;
    }

    @Override
    public String getTotalEmployees() throws SQLException, ClassNotFoundException {
        return employeeDAO.getTotal();
    }
}
