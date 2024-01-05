package lk.ijse.semisterfinal.model;

import com.ctc.wstx.osgi.WstxBundleActivator;
import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AtendanceDTO;
import lk.ijse.semisterfinal.dto.CusromerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtendanceModel {
    public static boolean addAttendance(AtendanceDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO attendance VALUES (?,?,?,?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.getDate());
            pstm.setString(2, dto.getEmployeeId());
            pstm.setString(3, dto.getEmployeeName());
            pstm.setString(4, dto.getPOra());

            return pstm.executeUpdate() > 0;
        }
    }

    public static List<AtendanceDTO> getAllatendance() throws SQLException{
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM attendance";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<AtendanceDTO> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new AtendanceDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)

                    )
            );

        }
        return dtoList;
    }

}
