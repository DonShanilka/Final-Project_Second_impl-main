package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.dto.SalaryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {
    public static boolean addSalary(SalaryDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO salary VALUES(?,?,?,?)";

        PreparedStatement ptm = connection.prepareStatement(sql);
        ptm.setString(1, dto.getDate());
        ptm.setString(2, dto.getEmployeeId());
        ptm.setString(3, dto.getEmployeeName());
        ptm.setDouble(4, dto.getSalary());



        boolean isSaved = ptm.executeUpdate()>0;
        return isSaved;
    }

    /*public static List<SalaryDTO> loadAllSalary() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM salary";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SalaryDTO> salaryList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            var dto =new SalaryDTO(
                    resultSet.getDate(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
            salaryList.add(dto);
        }

        return salaryList;
    }*/

    public static ArrayList<SalaryDTO> getAllSalary() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM salary";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SalaryDTO> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new SalaryDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return dtoList;
    }

    /*public static S searchSalaryId(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM item WHERE item_code = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        ItemDTO dto = null;

        if (resultSet.next()){
            String item_code = resultSet.getString(1);
            String item_name = resultSet.getString(2);
            double item_price = Double.parseDouble(resultSet.getString(3));
            String sup_id = resultSet.getString(4);
            String warranty = resultSet.getString(5);
            String qty = String.valueOf(resultSet.getInt(6));

            dto = new ItemDTO(item_code,item_name,item_price,sup_id,warranty,qty);
        }
        return dto;
    }*/

}
