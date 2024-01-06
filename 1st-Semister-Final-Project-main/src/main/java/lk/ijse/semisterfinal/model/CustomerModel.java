package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.CusromerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public static boolean AddCustomer(CusromerDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();


        String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getTxtCustId());
        ptm.setString(2, dto.getTxtCustAddress());
        ptm.setString(3, dto.getTxtCustName());
        ptm.setString(4, dto.getTxtCustMobile());
        ptm.setString(5, dto.getTxtCustitemId());
        ptm.setString(6, dto.getTxtCustPayment());

        return ptm.executeUpdate()>0;
    }

    public static boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "DELETE FROM customer WHERE customer_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        return pstm.executeUpdate()>0;
    }

    public static List<CusromerDTO> getAllCustomer() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT customer_id,customer_address,customer_name,customer_mobile,payment,item_id FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<CusromerDTO> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new CusromerDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );

        }
        return dtoList;
    }


    public static CusromerDTO searchCustomer(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM customer WHERE customer_id = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        CusromerDTO dto = null;

        if (resultSet.next()){
            String cust_id = resultSet.getString(1);
            String cust_address = resultSet.getString(2);
            String cust_name = resultSet.getString(3);
            String cust_mobile = resultSet.getString(4);
            String payment = resultSet.getString(5);

            dto = new CusromerDTO(cust_id,cust_address,cust_name,cust_mobile,payment);
        }
        return dto;
    }

    public static boolean updateCustomer(CusromerDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "UPDATE customer SET customer_address = ?, customer_name = ?, customer_mobile = ?, item_id = ? , payment =?  WHERE customer_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getTxtCustAddress());
        pstm.setString(2,dto.getTxtCustName());
        pstm.setString(3,dto.getTxtCustMobile());
        pstm.setString(4, dto.getTxtCustitemId());
        pstm.setString(5, dto.getTxtCustPayment());
        pstm.setString(6, dto.getTxtCustId());

        return pstm.executeUpdate() >0;

    }

}
