package lk.ijse.semisterfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.dto.EmployeeLogDTO;
import lk.ijse.semisterfinal.model.EmployeeLogModel;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeRegisterController {
    public AnchorPane anrood;
    public TextField empId;
    public TextField empName;
    public TextField empPassword;

    public void HyperLoginOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/employeeLogin.fxml"));
            anrood.getChildren().clear();
            anrood.getChildren().add(load);
        }

    private void clearField() {
        empId.setText("");
        empName.setText("");
        empPassword.setText("");

    }

    public void EmpRegisterOnAction(ActionEvent event) {
        String id = empId.getText();
        String name = empName.getText();
        String password = empPassword.getText();

        var dto = new EmployeeLogDTO(id,name,password);

        try {
            boolean isRegister= EmployeeLogModel.registerEmployee(dto);
            if (isRegister) {
                new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
