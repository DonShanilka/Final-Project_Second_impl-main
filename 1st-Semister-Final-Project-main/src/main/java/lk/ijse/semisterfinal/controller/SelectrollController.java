package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;



public class SelectrollController {
    public AnchorPane roodNode;
    public JFXButton btnAdmin;
    public JFXButton btnemployee;


    @FXML
    void btnAdminOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/adminlogin.fxml"));
        roodNode.getChildren().clear();
        roodNode.getChildren().add(load);

    }

    public void EmployeeOnaction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/employeeLogin.fxml"));
        roodNode.getChildren().clear();
        roodNode.getChildren().add(load);
    }
}
