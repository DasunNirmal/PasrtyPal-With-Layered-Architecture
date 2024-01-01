package lk.ijse.PastryPal.BO.Custom.impl;

import lk.ijse.PastryPal.BO.Custom.ComplainBO;
import lk.ijse.PastryPal.DAO.DAOFactory;
import lk.ijse.PastryPal.DAO.custom.ComplainDAO;
import lk.ijse.PastryPal.Entity.Complain;
import lk.ijse.PastryPal.dto.ComplainDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplainBOImpl implements ComplainBO {

    ComplainDAO complainDAO = (ComplainDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COMPLAIN);

    @Override
    public String generateNextComplainID() throws SQLException, ClassNotFoundException {
        return complainDAO.generateNextID();
    }

    @Override
    public boolean saveComplain(ComplainDto dto) throws SQLException, ClassNotFoundException {
        return complainDAO.save(new Complain(dto.getComplain_id(),dto.getDescription(),dto.getComplain_date()));
    }

    @Override
    public boolean updateComplain(ComplainDto dto) throws SQLException, ClassNotFoundException {
        return complainDAO.update(new Complain(dto.getComplain_id(),dto.getDescription(),dto.getComplain_date()));
    }

    @Override
    public boolean deleteComplain(String id) throws SQLException, ClassNotFoundException {
        return complainDAO.delete(id);
    }

    @Override
    public List<ComplainDto> getAllComplains() throws SQLException, ClassNotFoundException {
        ArrayList<ComplainDto> complainDto = new ArrayList<>();
        List<Complain> complains = complainDAO.getAll();

        for (Complain complain : complains) {
            complainDto.add(new ComplainDto(complain.getComplain_id(),complain.getDescription(),complain.getComplain_date()));
        }
        return complainDto;
    }

    @Override
    public String getTotalComplains() throws SQLException, ClassNotFoundException {
        return complainDAO.getTotal();
    }
}
