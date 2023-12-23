package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.CustomerTm;
import lk.ijse.semisterfinal.Tm.SupplierTm;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.dto.SupplierDTO;
import lk.ijse.semisterfinal.model.CustomerModel;
import lk.ijse.semisterfinal.model.SupplierModel;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddSupplierControlller  {
    public TextField txtSupName;
    public TextField txtSupId;
    public TableColumn <?,?> tmSupId;
    public TableColumn <?,?> tmSupName;
    public TextField txtSupQty;
    public TextField txtSupMobile;
    public AnchorPane rood;
    public TableView <SupplierTm> supplierAddTable;
    public Label lbltotalSup;
    public TextField txtEmail;
    public ChoiceBox <String> itemCatagoryBox;
    public TextField txtCompAddress;
    public TextField txtCompName;
    public TableColumn <?,?> tmMobile;
    public TableColumn <?,?> tmEmail;
    public TableColumn <?,?> tmcompName;
    public TableColumn <?,?> tmCompAddress;
    public TableColumn <?,?> tmItemCode;
    public TableColumn <?,?> tmItemDis;
    public TableColumn <?,?> tmQty;
    public TableColumn <?,?> tmBacthNum;
    public TableColumn <?,?> tmCatagory;
    public TextField txtItemDis;
    public TextField txtBnuM;
    public TextField txtItemCode;
    public TextField txtSupNic;

    String[] ca = { "Electrical", "Furniture", "Toys", "Exercise equipment", "Office equipment"};

    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllSupplier();
        tableListener();
        totalSupplier();
        itemCatagoryBox.getItems().addAll(ca);

    }

    private void tableListener() {
        supplierAddTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(SupplierTm row) {
        txtSupNic.setText(row.getSupId());
        txtSupName.setText(row.getSupName());
        txtSupMobile.setText(String.valueOf(row.getMobile()));
        txtEmail.setText(String.valueOf(row.getEmail()));
        txtCompName.setText(row.getCoName());
        txtCompAddress.setText(row.getCoAddress());
        txtItemCode.setText(String.valueOf(row.getItemcode()));
        txtItemDis.setText(row.getItemName());
        txtSupQty.setText(String.valueOf(row.getQty()));
        txtBnuM.setText(String.valueOf(row.getBNum()));
        itemCatagoryBox.setValue(String.valueOf(row.getCatagory()));
    }

    public void addSupplierOnAction(ActionEvent event) {
            String supId = txtSupNic.getText();
            String supName = txtSupName.getText();
            int mobile = Integer.parseInt(txtSupMobile.getText());
            String email = txtEmail.getText();
            String coName = txtCompName.getText();
            String coAddress = txtCompAddress.getText();
            int itemcode = Integer.parseInt(txtItemCode.getText());
            String itemName = txtItemDis.getText();
            int qty = Integer.parseInt(txtSupQty.getText());
            String bNum = txtBnuM.getText();
            String catagory = itemCatagoryBox.getValue();

            var dto = new SupplierDTO(supId,supName,mobile,email,coName,coAddress,itemcode,itemName,qty,bNum,catagory);

            try {
                boolean addSup = SupplierModel.addSuppliers(dto);
                if (addSup) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added").show();
                    loadAllSupplier();
                    clearField();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }


    private void clearField() {
        txtSupId.setText("");
        txtSupName.setText("");
        txtItemDis.setText("");
        txtSupQty.setText("");
        txtSupMobile.setText("");

    }

    public void deleteSupplierOnAction(ActionEvent event) {
        String id = txtSupId.getText();

        try {
            boolean isDeleted = SupplierModel.deleteSupplier(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier has deleted!").show();
                loadAllSupplier();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void updateSupplierOnAction(ActionEvent event) throws IOException {

        String supId = txtSupNic.getText();
        String supName = txtSupName.getText();
        int mobile = Integer.parseInt(txtSupMobile.getText());
        String email = txtEmail.getText();
        String coName = txtCompName.getText();
        String coAddress = txtCompAddress.getText();
        int itemcode = Integer.parseInt(txtItemCode.getText());
        String itemName = txtItemDis.getText();
        int qty = Integer.parseInt(txtSupQty.getText());
        String bNum = txtBnuM.getText();
        String catagory = itemCatagoryBox.getValue();

        var dto = new SupplierDTO(supName,mobile,email,coName,coAddress,itemcode,itemName,qty,bNum,catagory,supId);

        try {
            boolean updateSup = SupplierModel.updateSupplier(dto);
            if (updateSup) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Update").show();
                loadAllSupplier();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        /*Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/UpdateSupplier.fxml"))));
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                loadAllSupplier();
            }
        });
        stage.centerOnScreen();
        stage.show();*/
    }

    private void setCellValueFactory() {
        tmSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        tmSupName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        tmMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tmcompName.setCellValueFactory(new PropertyValueFactory<>("coName"));
        tmCompAddress.setCellValueFactory(new PropertyValueFactory<>("coAddress"));
        tmItemCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        tmItemDis.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tmBacthNum.setCellValueFactory(new PropertyValueFactory<>("bNum"));
        tmCatagory.setCellValueFactory(new PropertyValueFactory<>("catagory"));

    }

    private void loadAllSupplier() {

        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<SupplierDTO> dtoList = SupplierModel.getAllSupplier();

            for (SupplierDTO dto : dtoList) {
                obList.add(
                        new SupplierTm(
                                dto.getSupNic(),
                                dto.getSupName(),
                                dto.getMobile(),
                                dto.getEmail(),
                                dto.getCoName(),
                                dto.getCoAddress(),
                                dto.getItemcode(),
                                dto.getItemName(),
                                dto.getQty(),
                                dto.getBNum(),
                                dto.getCatagory()

                        )
                );
            }

            supplierAddTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void totalSupplier() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT COUNT(supplier_id) FROM supplier";

        String totalSup = null;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalSup = resultSet.getString("COUNT(supplier_id)");
            }
            lbltotalSup.setText(totalSup);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

