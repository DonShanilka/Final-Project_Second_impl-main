package lk.ijse.semisterfinal.model;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.semisterfinal.DB.DbConnetion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class BillModel {
    public boolean saveBillDetails(String orderId, String itemId, String itemName, Label itemPrice, LocalDate date, TextField qty, TextField discount, Label total, TextField payment, Label balance) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO bill VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, orderId);
        pstm.setNString(2, itemId);
        pstm.setString(3, itemName);
        pstm.setNString(4, String.valueOf(itemPrice));
        pstm.setDate(5, Date.valueOf(date));
        pstm.setNString(6, String.valueOf(qty));
        pstm.setNString(7, String.valueOf(discount));
        pstm.setNString(8, String.valueOf(total));
        pstm.setNString(9, String.valueOf(payment));
        pstm.setNString(10, String.valueOf(balance));

        return pstm.executeUpdate() < 0;
    }

}
