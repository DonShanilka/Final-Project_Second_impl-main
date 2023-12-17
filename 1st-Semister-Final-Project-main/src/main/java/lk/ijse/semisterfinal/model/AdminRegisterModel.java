package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AdminDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminRegisterModel {
    public boolean registerAdmin(AdminDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();


        String sql = "INSERT INTO admin VALUES(?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getId());
        ptm.setString(2, dto.getUserName());
        ptm.setString(3, dto.getPassword());

        return ptm.executeUpdate()>0;

    }
}
