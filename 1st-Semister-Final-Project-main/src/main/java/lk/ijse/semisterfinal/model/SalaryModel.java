package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.AtendanceDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.dto.SalaryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {
    public static boolean addSalary(SalaryDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO salary VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ptm = connection.prepareStatement(sql);
        ptm.setString(1, dto.getDate());
        ptm.setString(2, dto.getEmployeeId());
        ptm.setString(3, dto.getEmployeeName());
        ptm.setDouble(4, dto.getSalary());
        ptm.setInt(5, dto.getOtcount());
        ptm.setDouble(6,dto.getPay1h());
        ptm.setDouble(7, dto.getBonase());
        ptm.setInt(8, dto.getEpf());
        ptm.setInt(9, dto.getEtf());
        ptm.setInt(10, dto.getPrCount());
        ptm.setInt(11, dto.getAbcount());
        ptm.setDouble(12, dto.getTotalsalary());

        boolean isSaved = ptm.executeUpdate()>0;
        return isSaved;
    }


    public static ArrayList<SalaryDTO> getAllSalary() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM salary";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SalaryDTO> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new SalaryDTO(
                            String.valueOf(resultSet.getDate(1)),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getInt(5),
                            resultSet.getDouble(6),
                            resultSet.getDouble(7),
                            resultSet.getInt(8),
                            resultSet.getInt(9),
                            resultSet.getInt(10),
                            resultSet.getInt(11),
                            resultSet.getDouble(12)
                    )
            );
        }
        return dtoList;
    }

    public static AtendanceDTO getABcount(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        //String sqlPr = "SELECT COUNT(presentAbsent) FROM attendance WHERE employee_id  = 'Present'";
        String sqlAb = "SELECT COUNT(*) FROM attendance WHERE employee_id = ? AND presentAbsent = 'Absent'";

        PreparedStatement pstm = connection.prepareStatement(sqlAb);
        pstm.setString(1, String.valueOf(id));

        ResultSet resultSet = pstm.executeQuery();

        AtendanceDTO dto = null;

        if (resultSet.next()) {
            String ab = resultSet.getString(1);

            dto = new AtendanceDTO(ab);
        }
        return dto;
    }


    public static AtendanceDTO getPRcount(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        //String sqlPr = "SELECT COUNT(presentAbsent) FROM attendance WHERE employee_id  = 'Present'";
        String sqlAb = "SELECT COUNT(*) FROM attendance WHERE employee_id = ? AND presentAbsent = 'Present'";

        PreparedStatement pstm = connection.prepareStatement(sqlAb);
        pstm.setString(1, String.valueOf(id));

        ResultSet resultSet = pstm.executeQuery();

        AtendanceDTO dto = null;

        if (resultSet.next()) {
            String pr = resultSet.getString(1);

            dto = new AtendanceDTO(pr);
        }
        return dto;
    }


    public static boolean deleteSalary(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "DELETE FROM salary WHERE employee_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        return pstm.executeUpdate()>0;
    }

}
