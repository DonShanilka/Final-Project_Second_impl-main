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

        String sql = "INSERT INTO supplier VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getSupNic());
        ptm.setString(2, dto.getSupName());
        ptm.setInt(3, dto.getMobile());
        ptm.setString(4, dto.getEmail());
        ptm.setString(5, dto.getCoName());
        ptm.setString(6, dto.getCoAddress());
        ptm.setInt(7, dto.getItemcode());
        ptm.setString(8, dto.getItemName());
        ptm.setInt(9, dto.getQty());
        ptm.setString(10, dto.getBNum());
        ptm.setString(11, dto.getCatagory());

        return ptm.executeUpdate()>0;

    }

    public static boolean deleteSupplier(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE supNic = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }


    public static boolean updateSupplier(SupplierDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "UPDATE supplier SET supName = ?,mobile = ?, email = ?, companyName = ?, companyAddress = ?, itemCode = ?, itemName = ?, quantity = ?, businessNumber = ?, category = ? WHERE supNic = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getSupName());
        pstm.setInt(2,dto.getMobile());
        pstm.setString(3,dto.getEmail());
        pstm.setString(4, dto.getCoName());
        pstm.setString(5, dto.getCoAddress());
        pstm.setInt(6, dto.getItemcode());
        pstm.setString(7, dto.getItemName());
        pstm.setInt(8, dto.getQty());
        pstm.setString(9, dto.getBNum());
        pstm.setString(10, dto.getCatagory());
        pstm.setString(11, dto.getSupNic());

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
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getInt(7),
                            resultSet.getString(8),
                            resultSet.getInt(9),
                            resultSet.getString(10),
                            resultSet.getString(11)
                    )
            );
        }
        return dtoList;

    }

    public static SupplierDTO searchsupplier(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM supplier WHERE supNic = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        SupplierDTO dto = null;

        if (resultSet.next()){
            String supId = resultSet.getString(1);
            String supName = resultSet.getString(2);
            int mobile = resultSet.getInt(3);
            String email = resultSet.getString(4);
            String coName = resultSet.getString(5);
            String coAddress = resultSet.getString(6);
            int itemcode = resultSet.getInt(7);
            String itemName = resultSet.getString(8);
            int qty = resultSet.getInt(9);
            String bNum = resultSet.getString(10);
            String catagory = resultSet.getString(11);

            dto = new SupplierDTO(supId,supName,mobile,email,coName,coAddress,itemcode,itemName,qty,bNum,catagory);

        }
        return dto;
    }

    public SupplierDTO search(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
