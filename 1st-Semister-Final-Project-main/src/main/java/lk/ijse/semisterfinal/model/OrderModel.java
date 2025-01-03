package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import java.sql.*;
import java.time.LocalDate;

public class OrderModel {
    public boolean saveOrder(String orderId, String customerId, LocalDate date) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setString(2, customerId);
        pstm.setDate(3, Date.valueOf(date));

        return pstm.executeUpdate() > 0;
    }

    public String getLastId() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return (resultSet.getString(1));
        }
        return null;
    }
}
