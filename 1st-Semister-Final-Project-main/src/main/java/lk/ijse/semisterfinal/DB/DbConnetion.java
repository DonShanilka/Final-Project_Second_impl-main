package lk.ijse.semisterfinal.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnetion {
    private static DbConnetion DbConnetion;
    private Connection connection;

    private DbConnetion() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MegaMart",
                "root",
                "Shanilka800@#");
    }

    public static DbConnetion getInstance() throws SQLException {
        return (null == DbConnetion) ? new DbConnetion(): DbConnetion;
    }

    public Connection getConnection(){
        return connection;
    }
}


