package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.model.ItemModel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class UpdateItemController {
    public ComboBox itemId;
    public TextField itemPrice;
    public TextField itemName;
    public TextField supplierId;
    public TextField waranty;
    public TextField qty;

    public void initialize() {
        setValue();
    }

    private void setValue() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<ItemDTO> idList = ItemModel.getAllItem();

            for (ItemDTO dto : idList) {
                obList.add(dto.getItemCode());
            }
            itemId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean validateEmployee() {
        boolean isValidate = true;
        boolean name = Pattern.matches("[A-Za-z]{4,}", itemName.getText());
        if (!name) {
            showErrorNotification("Invalid Item Name", "The Item name you entered is invalid");
            isValidate = false;
        }
        boolean con = Pattern.matches("[0-9]{0,}",qty.getText());
        if (!con){
            showErrorNotification("Invalid Contact Number", "The contact number you entered is invalid");
            isValidate = false;
        }

        /*boolean Job = Pattern.matches("[A-Za-z]{5,}",updateEmpPosition.getText());
        if (!Job){
            showErrorNotification("Invalid job type", "The job type you entered is invalid");
            isValidate = false;
        }*/
        return isValidate;
    }



    private void showErrorNotification(String title, String text) {
        Notifications.create()
                .title(title)
                .text(text)
                .showError();
    }

    private void clearFileds() {
        itemId.setValue("");
        itemName.setText("");
        itemPrice.setText("");
        supplierId.setText("");
        waranty.setText("");
        qty.setText("");
    }


    public void BackOnAction(ActionEvent event) {

    }

    public void comItemIdOnAction(ActionEvent event) {
        String id = (String) itemId.getValue();

        try{
            ItemDTO dto = ItemModel.searchItemId(id);
            itemName.setText(dto.getItemDetails());
            itemPrice.setText(String.valueOf(dto.getItemPrice()));
            supplierId.setText(dto.getSupplierId());
            waranty.setText(dto.getWarrantyPeriod());
            qty.setText(String.valueOf(dto.getItemQty()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void itemUpdateSaveOnAction(ActionEvent event) {
        /*String id = (String) itemId.getValue();
        String name = itemName.getText();
        double price = Double.parseDouble(itemPrice.getText());
        String supid = supplierId.getText();
        String warranty = waranty.getText();
        int Qty = Integer.parseInt(qty.getText());
        int cat = .getText());

        try{
            if (!validateEmployee()){
                return;
            }
            var dto = new ItemDTO(id,name,price,supid,warranty,Qty);
            boolean isUpdate = ItemModel.updateItem(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee is updated").show();
                clearFileds();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }*/
    }
}
