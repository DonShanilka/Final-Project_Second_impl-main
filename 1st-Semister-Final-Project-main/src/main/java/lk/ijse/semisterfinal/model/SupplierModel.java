package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.SupplierDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public static boolean addSuppliers(SupplierDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO supplier VALUES(?,?,?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getSupId());
        ptm.setString(2, dto.getSupName());
        ptm.setString(3, dto.getSupItemName());
        ptm.setString(4, String.valueOf(dto.getSupqty()));
        ptm.setString(5, dto.getSupMobile());

        return ptm.executeUpdate()>0;

    }

    public static List<SupplierDTO> loadAllSupplier() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SupplierDTO> supidlist = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            supidlist.add(new SupplierDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)

            ));
        }

        return supidlist;
    }

    public static boolean deleteSupplier(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE supplier_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }


    public static boolean updateSupplier(SupplierDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "UPDATE supplier SET supplier_name = ?, item_name = ?, qty = ?, supplier_mobile = ?  WHERE supplier_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getSupName());
        pstm.setString(2,dto.getSupItemName());
        pstm.setInt(3,dto.getSupqty());
        pstm.setString(4, dto.getSupMobile());
        pstm.setString(5, dto.getSupId());

        return pstm.executeUpdate() >0;

    }

    public static ArrayList<SupplierDTO> getAllSupplier() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SupplierDTO> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new SupplierDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;

    }

    public static SupplierDTO searchsupplier(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM supplier WHERE supplier_id = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        SupplierDTO dto = null;

        if (resultSet.next()){
            String sid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String itemName = resultSet.getString(3);
            int Qty = resultSet.getInt(4);
            String mobile = resultSet.getString(5);

            dto = new SupplierDTO(sid,name,itemName,Qty,mobile);
        }
        return dto;
    }

    public SupplierDTO search(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
