package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.EmployeeLogDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeLogModel {
    public static boolean registerEmployee(EmployeeLogDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();


        String sql = "INSERT INTO user VALUES(?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getE_id());
        ptm.setString(2, dto.getE_name());
        ptm.setString(3, dto.getE_password());


        return ptm.executeUpdate()>0;
    }
}
