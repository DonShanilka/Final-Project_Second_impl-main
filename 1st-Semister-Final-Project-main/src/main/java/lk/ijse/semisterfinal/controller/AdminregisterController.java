package lk.ijse.semisterfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.dto.AdminDTO;
import lk.ijse.semisterfinal.model.AdminRegisterModel;

import java.io.IOException;
import java.sql.SQLException;

public class AdminregisterController {
    public TextField enterAdminName;
    public TextField enterAdminId;
    public TextField enterPassword;
    public AnchorPane admingegister;

    private AdminRegisterModel adminRegisterModel = new AdminRegisterModel();
    public AdminMainDashbordController adminMainDashbordController = new AdminMainDashbordController();

    public AdminregisterController() throws SQLException {
    }


    public void HyperOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/adminlogin.fxml"));
        admingegister.getChildren().clear();
        admingegister.getChildren().add(load);
    }

    private void clearField() {
        enterAdminId.setText("");
        enterAdminName.setText("");
        enterPassword.setText("");

    }

    public void RegisterOnAction(ActionEvent event) {
            String id = enterAdminId.getText();
            String name = enterAdminName.getText();
            String password = enterPassword.getText();

            var dto = new AdminDTO(id,name,password);

            try {
                boolean isRegister= adminRegisterModel.registerAdmin(dto);
                if (isRegister) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
                    clearField();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
    }

}
