package lk.ijse.PastryPal.DAO.custom.impl;

import lk.ijse.PastryPal.DAO.custom.ComplainDAO;
import lk.ijse.PastryPal.DAO.SQLUtil;
import lk.ijse.PastryPal.dto.ComplainDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplainDAOImpl implements ComplainDAO {

    private String splitComplainID(String currentComplainID){
        if (currentComplainID != null){
            String [] split = currentComplainID.split("[M]");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("M%03d",id);
        }else {
            return "M001";
        }
    }
    @Override
    public String generateNextComplainID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT complain_id FROM complains ORDER BY complain_id DESC LIMIT 1");
        if (resultSet.next()){
            return splitComplainID(resultSet.getString(1));
        }
        return splitComplainID(null);
    }

    @Override
    public boolean saveComplain(ComplainDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO complains VALUES (?,?,?)",dto.getComplain_id(),dto.getDescription(),
                dto.getComplain_date());
    }

    @Override
    public boolean updateComplains(ComplainDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE complains SET description = ?,complain_date = ? WHERE complain_id = ?",
                dto.getDescription(),dto.getComplain_date(),dto.getComplain_id());
    }

    @Override
    public boolean deleteComplains(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM complains WHERE complain_id = ?",id);
    }

    @Override
    public List<ComplainDto> getAllComplains() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM complains");

        ArrayList<ComplainDto> dtoList = new ArrayList<>();
        while (resultSet.next()){
            dtoList.add(
                    new ComplainDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDate(3).toLocalDate()
                    ));
        }
        return dtoList;
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) FROM complains");
        resultSet.next();
        return resultSet.getString(1);
    }
}
