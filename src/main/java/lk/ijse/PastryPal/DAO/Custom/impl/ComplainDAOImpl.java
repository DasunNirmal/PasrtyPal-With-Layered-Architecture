package lk.ijse.PastryPal.DAO.Custom.impl;

import lk.ijse.PastryPal.DAO.Custom.ComplainDAO;
import lk.ijse.PastryPal.DB.DbConnection;
import lk.ijse.PastryPal.dto.ComplainDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public String generateNextComplainID() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT complain_id FROM complains ORDER BY complain_id DESC LIMIT 1";
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ResultSet resultSet = ptsm.executeQuery();
        if (resultSet.next()){
            return splitComplainID(resultSet.getString(1));
        }
        return splitComplainID(null);
    }

    @Override
    public boolean saveComplain(ComplainDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO complains VALUES (?,?,?)";
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setString(1,dto.getComplain_id());
        ptsm.setString(2,dto.getDescription());
        ptsm.setString(3, String.valueOf(dto.getComplain_date()));

        return ptsm.executeUpdate() > 0;
    }

    @Override
    public boolean updateComplains(ComplainDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE complains SET description = ?,complain_date = ? WHERE complain_id = ?";
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setString(1, dto.getDescription());
        ptsm.setString(2, String.valueOf(dto.getComplain_date()));
        ptsm.setString(3, dto.getComplain_id());

        return  ptsm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteComplains(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM complains WHERE complain_id = ?";
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setString(1,id);
        return ptsm.executeUpdate() > 0;
    }

    @Override
    public List<ComplainDto> getAllComplains() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM complains";
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ResultSet resultSet = ptsm.executeQuery();

        ArrayList<ComplainDto> dtoList = new ArrayList<>();
        while (resultSet.next()){
            dtoList.add(
                    new ComplainDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDate(3).toLocalDate()
                    )
            );
        }
        return dtoList;
    }
}
