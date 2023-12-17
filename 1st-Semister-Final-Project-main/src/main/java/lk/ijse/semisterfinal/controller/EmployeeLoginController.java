package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.semisterfinal.DB.DbConnetion;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeLoginController {

    public AnchorPane anroodNode;
    public TextField empLoginName;
    public TextField EmpPassword;
    public JFXButton empback;

    private void clearField() {
        empLoginName.setText("");
        EmpPassword.setText("");
    }

    public void HyperRegisterEmp(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/employeeRegister.fxml"));
        anroodNode.getChildren().clear();
        anroodNode.getChildren().add(load);
    }

    public void EmpBackOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/selectroll.fxml"));
        anroodNode.getChildren().clear();
        anroodNode.getChildren().add(load);
    }

    public void LoginOnEmp(ActionEvent event) throws SQLException {
        String username = empLoginName.getText();
        String password = EmpPassword.getText();
        Connection connection = DbConnetion.getInstance().getConnection();
        String sql = "SELECT * FROM user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            if(username.isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Empty").show();
                return;
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/EmployeeDhashborad.fxml"));
                Scene scene = new Scene(rootNode);
                Stage primaryStage = (Stage) this.anroodNode.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Dashboard");
            } else {
                new Alert(Alert.AlertType.ERROR, "oops! credentials are wrong!").show();
                clearField();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            clearField();
        }
    }

}
