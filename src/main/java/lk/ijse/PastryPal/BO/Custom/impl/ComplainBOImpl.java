package lk.ijse.PastryPal.BO.Custom.impl;

import lk.ijse.PastryPal.BO.Custom.ComplainBO;
import lk.ijse.PastryPal.DAO.DAOFactory;
import lk.ijse.PastryPal.DAO.custom.ComplainDAO;
import lk.ijse.PastryPal.Entity.Complain;
import lk.ijse.PastryPal.dto.ComplainDto;

import java.sql.SQLException;
import java.util.List;

public class ComplainBOImpl implements ComplainBO {

    ComplainDAO complainDAO = (ComplainDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COMPLAIN);

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return complainDAO.generateNextID();
    }

    @Override
    public boolean save(ComplainDto dto) throws SQLException, ClassNotFoundException {
        return complainDAO.save(new Complain(dto.getComplain_id(),dto.getDescription(),dto.getComplain_date()));
    }

    @Override
    public boolean update(ComplainDto dto) throws SQLException, ClassNotFoundException {
        return complainDAO.update(new Complain(dto.getComplain_id(),dto.getDescription(),dto.getComplain_date()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<ComplainDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getTotal() throws SQLException, ClassNotFoundException {
        return null;
    }
}
