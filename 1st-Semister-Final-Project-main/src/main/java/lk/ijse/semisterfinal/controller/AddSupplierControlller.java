package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddSupplierControlller {
    public TextField txtSupName;
    public TextField txtSupId;
    public TextField txtsupItemName;
    public TableColumn <?,?> tmSupId;
    public TableColumn <?,?> tmSupName;
    public TableColumn <?,?> supItemName;
    public TextField txtSupQty;
    public TextField txtSupMobile;
    public TableColumn <?,?> tmqty;
    public TableColumn <?,?> tmSupMobile;
    public AnchorPane rood;
    public TableView <SupplierTm> supplierAddTable;
    public Label lbltotalSup;
    public TextField txtEmail;
    public TextField txtEmail1;
    public TextField txtEmail2;


    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllSupplier();
        tableListener();
        totalSupplier();
    }

    private void tableListener() {
        supplierAddTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(SupplierTm row) {
        txtSupId.setText(row.getSupId());
        txtSupName.setText(row.getSupName());
        txtsupItemName.setText(row.getSupItemName());
        txtSupQty.setText(String.valueOf(row.getSupqty()));
        txtSupMobile.setText(String.valueOf(row.getSupMobile()));
    }

    public void addSupplierOnAction(ActionEvent event) {
            String supId = txtSupId.getText();
            String supName = txtSupName.getText();
            String supItemName = txtsupItemName.getText();
            int supqty = Integer.parseInt(txtSupQty.getText());
            String supMobile = txtSupMobile.getText();

            var dto = new SupplierDTO(supId,supName,supItemName,supqty,supMobile);

            try {
                boolean addSup = SupplierModel.addSuppliers(dto);
                if (addSup) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added").show();
                    loadAllSupplier();
                    clearField();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }


    private void clearField() {
        txtSupId.setText("");
        txtSupName.setText("");
        txtsupItemName.setText("");
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
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/UpdateSupplier.fxml"))));
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                loadAllSupplier();
            }
        });
        stage.centerOnScreen();
        stage.show();
    }

    private void setCellValueFactory() {
        tmSupId.setCellValueFactory(new PropertyValueFactory<>("SupId"));
        tmSupName.setCellValueFactory(new PropertyValueFactory<>("SupName"));
        supItemName.setCellValueFactory(new PropertyValueFactory<>("SupItemName"));
        tmqty.setCellValueFactory(new PropertyValueFactory<>("supqty"));
        tmSupMobile.setCellValueFactory(new PropertyValueFactory<>("supMobile"));

    }

    private void loadAllSupplier() {

        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<SupplierDTO> dtoList = SupplierModel.getAllSupplier();

            for (SupplierDTO dto : dtoList) {
                obList.add(
                        new SupplierTm(
                                dto.getSupId(),
                                dto.getSupName(),
                                dto.getSupItemName(),
                                dto.getSupqty(),
                                dto.getSupMobile()

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

