package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.semisterfinal.dto.SupplierDTO;
import lk.ijse.semisterfinal.model.SupplierModel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class UpdateSupplierController {

    public ComboBox supId;
    public TextField itemName;
    public TextField supName;
    public TextField supMobile;
    public TextField qty;

    public void comUpdateSupplier(ActionEvent event) {
        String id = (String) supId.getValue();

        try{
            SupplierDTO dto = SupplierModel.searchsupplier(id);
            /*supName.setText(dto.getSupName());
            itemName.setText(dto.getSupItemName());
            qty.setText(String.valueOf(dto.getSupqty()));
            supMobile.setText(dto.getSupMobile());*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void itemUpdateSaveOnAction(ActionEvent event) {
        /*String id = (String) supId.getValue();
        String name = supName.getText();
        String iName = itemName.getText();
        int Qty = Integer.parseInt(qty.getText());
        String sMobile = supMobile.getText();

        try{
            if (!validateEmployee()){
                return;
            }
            var dto = new SupplierDTO(id,name,iName,Qty,sMobile);
            boolean isUpdate = SupplierModel.updateSupplier(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier is updated").show();
                clearFileds();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }*/
    }

    public void initialize() {
        setValue();
    }

    private void setValue() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> idList = SupplierModel.getAllSupplier();

            for (SupplierDTO dto : idList) {
                obList.add(dto.getSupNic());
            }
            supId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean validateEmployee() {
        boolean isValidate = true;
        boolean name = Pattern.matches("[A-Za-z]{4,}", supName.getText());
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
        supId.setValue("");
        supName.setText("");
        itemName.setText("");
        qty.setText("");
        supMobile.setText("");

    }


    public void BackOnAction(ActionEvent event) {

    }


}
