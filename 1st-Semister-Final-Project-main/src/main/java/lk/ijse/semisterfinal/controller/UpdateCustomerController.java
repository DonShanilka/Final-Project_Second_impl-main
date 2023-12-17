package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;
import lk.ijse.semisterfinal.model.CustomerModel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class UpdateCustomerController {

    public ComboBox customerId;
    public TextField customerAddress;
    public TextField customerName;
    public TextField customerMobile;
    public TextField itemId;
    public TextField payment;

    public void initialize() {
        setValue();

    }

    private boolean validateEmployee() {
        boolean isValidate = true;
        boolean id = Pattern.matches("[C]{0,}", customerId.getValue().toString());
        if (!id){
            showErrorNotification("Invalid Customer Id", "The Customer Id you entered is invalid");
            isValidate = false;
        }
        boolean name = Pattern.matches("[A-Za-z]{5,}", customerName.getText());
        if (!name){
            showErrorNotification("Invalid Customer Name", "The Customer name you entered is invalid");
            isValidate = false;
        }
        boolean con = Pattern.matches("[0-9]{10}",customerMobile.getText());
        if (!con){
            showErrorNotification("Invalid Contact Number", "The contact number you entered is invalid");
            isValidate = false;
        }

        boolean Job = Pattern.matches("[A-Za-z]{5,}",customerAddress.getText());
        if (!Job){
            showErrorNotification("Invalid Address", "The Address you entered is invalid");
            isValidate = false;
        }
        return isValidate;
    }

    private void showErrorNotification(String title, String text) {
        Notifications.create()
                .title(title)
                .text(text)
                .showError();
    }

    private void clearFileds() {
        customerId.setValue("");
        customerAddress.setText("");
        customerName.setText("");
        customerMobile.setText("");
        itemId.setText("");
        payment.setText("");
    }

    public void itemUpdateSaveOnAction(ActionEvent event) {
        String id = (String) customerId.getValue();
        String address = customerAddress.getText();
        String name = customerName.getText();
        String contact = customerMobile.getText();
        String item_id = itemId.getText();
        String  Payment = payment.getText();

        try{
            if (!validateEmployee()){
                return;
            }
            var dto = new CusromerDTO(id,address,name,contact,item_id,Payment);
            boolean isUpdate = CustomerModel.updateCustomer(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee is updated").show();
                clearFileds();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void comcustomerId(ActionEvent event) {
            String id = (String) customerId.getValue();

            try{
                CusromerDTO dto = CustomerModel.searchCustomer(id);
                customerAddress.setText(dto.getTxtCustAddress());
                customerName.setText(dto.getTxtCustName());
                customerMobile.setText(dto.getTxtCustMobile());
                itemId.setText(dto.getTxtCustitemId());
                payment.setText(dto.getTxtCustPayment());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    private void setValue() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<CusromerDTO> idList = CustomerModel.getAllCustomer();

            for ( CusromerDTO dto : idList) {
                obList.add(dto.getTxtCustId());
            }
            customerId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void BackOnAction(ActionEvent event) {

    }

}



