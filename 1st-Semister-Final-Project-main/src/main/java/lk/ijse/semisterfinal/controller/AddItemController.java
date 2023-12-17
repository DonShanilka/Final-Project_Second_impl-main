package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.ItemTm;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.dto.SupplierDTO;
import lk.ijse.semisterfinal.model.CustomerModel;
import lk.ijse.semisterfinal.model.ItemModel;
import lk.ijse.semisterfinal.model.SupplierModel;
import org.controlsfx.control.Notifications;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static java.awt.SystemColor.text;

public class AddItemController implements Initializable {
    public TextField txtItemCode;
    public TextField txtItemPrice;
    public TextField txtWarrantyPeriod;
    public TextArea txtitemDetails;
    public ComboBox  comsupid;

    public Pane root;
    public TableView<lk.ijse.semisterfinal.Tm.ItemTm> ItemTm;
    public TableColumn <?,?> tmItemCode;
    public TableColumn <?,?> tmItemDetails;
    public TableColumn <?,?> tmItemPrice;
    public TableColumn <?,?> tmSupplierId;
    public TableColumn <?,?> tmWarranty;
    public TextField txtQty;
    public TextField serachItem;
    @FXML
    public Label lblTotalItem;
    public TableColumn <?,?> tmQty;

    public void initialize() throws SQLException {
        totalItem();
        setCellValueFactory();
        tableListener();
        loadAllItem();
        itemSerachOnAction();
    }

    private void tableListener() {
        ItemTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);

        });
    }

    private void setData(ItemTm row) {
        txtItemCode.setText(row.getItemCode());
        txtitemDetails.setText(row.getItemDetails());
        txtItemPrice.setText(String.valueOf(row.getItemPrice()));
        comsupid.setValue(row.getSupplierId());
        txtWarrantyPeriod.setText(row.getWarrantyPeriod());
        txtQty.setText(String.valueOf(row.getItemQty()));

    }

    private void clearField() {
        txtItemCode.setText("");
        comsupid.setValue("");
        txtItemPrice.setText("");
        txtWarrantyPeriod.setText("");
        txtitemDetails.setText("");
        txtQty.setText("");

    }

    public void AddItemOnAction(ActionEvent event) {
        validateCustomer();

        String ItemCode = txtItemCode.getText();
        String ItemName = txtitemDetails.getText();
        double ItemPrice = Double.parseDouble(txtItemPrice.getText());
        String SupplierId = (String) comsupid.getValue();
        String WarrantyPeriod = txtWarrantyPeriod.getText();
        int qty  = Integer.parseInt(txtQty.getText());


        var dto = new ItemDTO(ItemCode,ItemName,ItemPrice,SupplierId,WarrantyPeriod,qty);

        try {
            boolean isaddite = ItemModel.addItem(dto);
            if (isaddite) {
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
                loadAllItem();
                clearField();
                itemSerachOnAction();
                totalItem();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void cmbsupidOnAction(ActionEvent event) {
        String id = (String) comsupid.getValue();
        try {
            SupplierDTO dto = SupplierModel.searchsupplier(id);
            tmSupplierId.setText(dto.getSupId());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void loadAllItem() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<ItemDTO> dtoList = ItemModel.getAllItem();

            for (ItemDTO dto : dtoList) {
                obList.add(
                        new ItemTm(
                                dto.getItemCode(),
                                dto.getItemDetails(),
                                dto.getItemPrice(),
                                dto.getSupplierId(),
                                dto.getWarrantyPeriod(),
                                dto.getItemQty()

                        ));
            }

            ItemTm.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*private void loadAllSupId() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<SupplierDTO> dtoList = SupplierModel.getAllSupplier();

            for (SupplierDTO dto : dtoList) {
                obList.add(
                        new SupplierTm(
                                dto.getSupId(),
                                dto.getSupName(),
                                dto.getSupItemName(),
                                dto.getSupMobile(),
                                dto.getSupqty()
                        ));
            }
            SupplierTm.
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    private void setCellValueFactory() {
        tmItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        tmItemDetails.setCellValueFactory(new PropertyValueFactory<>("itemDetails"));
        tmItemPrice.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
        tmSupplierId.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        tmWarranty.setCellValueFactory(new PropertyValueFactory<>("WarrantyPeriod"));
        tmQty.setCellValueFactory(new PropertyValueFactory<>("ItemQty"));

    }

    public void UpdateOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/UpdateItem.fxml"))));
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                loadAllItem();
                try {
                    totalItem();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stage.centerOnScreen();
        stage.show();
    }

    public void deleteOnAction(ActionEvent event) {
        String id = txtItemCode.getText();

        try {
            boolean isDeleted = ItemModel.deleteItem(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item has deleted!").show();
                loadAllItem();
                totalItem();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Item not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void cmbSupplierOnAction(ActionEvent event) {
        String id = (String) comsupid.getValue();

        try {
            SupplierDTO dto = SupplierModel.searchsupplier(id);
            comsupid.setValue(dto.getSupId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void itemSerachOnAction() {
        FilteredList<ItemTm> filteredData = new FilteredList<>(ItemTm.getItems(), b -> true);

        serachItem.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String serchKey = newValue.toLowerCase();

                if (item.getItemCode().toString().contains(serchKey)) {
                    return true;
                } else if (item.getItemDetails().toLowerCase().contains(serchKey)){
                    return true;
                } else return false;
            });
        });

        SortedList <ItemTm> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(ItemTm.comparatorProperty());
        ItemTm.setItems(sortedList);
    }

    public void totalItem() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT COUNT(item_code) FROM item";

        String totalItem = null;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalItem = resultSet.getString("COUNT(item_code)");
            }
            lblTotalItem.setText(totalItem);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            totalItem();
            setCellValueFactory();
            tableListener();
            loadAllItem();
            itemSerachOnAction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateCustomer() {
        boolean isValidate = true;
        boolean id = Pattern.matches("[A-Za-z]{0,}",txtitemDetails.getText());
        if (!id){
            showErrorNotification("Invalid id", "The id you entered is invalid");
            isValidate = false;
        }
        boolean details = Pattern.matches("[A-Za-z]{0,}",txtitemDetails.getText());
        if (!details){
            showErrorNotification("Invalid details", "The details you entered is invalid");
            isValidate = false;
        }
        boolean qty = Pattern.matches("[0-9]{0,}",txtQty.getText());
        if (!qty){
            showErrorNotification("Invalid Qty", "The Qty Number you entered is invalid");
            isValidate = false;

        }
        return isValidate;
    }

    private void showErrorNotification(String title, String txtt) {
        Notifications.create()
                .title(title)
                .text(String.valueOf(text))
                .showError();
    }
}



