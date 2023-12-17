package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class AdminMainDashbordController {

    SelectrollController selectrollController = new SelectrollController();

    public JFXButton monthlyIncome;
    public JFXButton item;
    public JFXButton Employee;
    public JFXButton supplier;
    public JFXButton customer;
    public JFXButton salary;
    public AnchorPane root;
    public AnchorPane root1;
    public JFXButton cashiyer;
    public JFXButton attendence;

    @FXML
    public BarChart <?,?> orderDataChart;
    @FXML
    public Label lblTotalOrders;
    @FXML
    public Label lblTotalUnits;
    @FXML
    public Label lblTotalInCome;
    @FXML
    public AreaChart <?,?> incomeDataChart;


    public void initialize() throws IOException {
        monthlyIncomeOnAction(null);
    }

    void setForm(String form) throws IOException {
        String[] formArray = {"/view/AddCustomer.fxml", "/view/addEmployee.fxml", "/view/AddItem.fxml", "/view/AddSupplier.fxml","/view/Salary.fxml" , "/view/monthlyincome.fxml","/view/Attendance.fxml","/view/Cashier.fxml"};

        JFXButton[] btnArray = {customer,Employee,item,supplier,salary,monthlyIncome,attendence,cashiyer};
        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(form)));
        root1.getChildren().clear();
        root1.getChildren().add(load);
        for (int i = 0; i < formArray.length; i++) {
            btnArray[i].setStyle("/*-fx-background-color:  #ffffff;*/ -fx-font-size: 1; /*-fx-text-fill: #202A44FF*/");

            if (form.equals(formArray[i])){
                btnArray[i].setStyle("/*-fx-background-color: #ffffff;*/ -fx-font-size: 20 /*-fx-text-fill: #ffffff*/");
            }
        }
    }

    public void monthlyIncomeOnAction(ActionEvent event) throws IOException {
        setForm("/view/monthlyincome.fxml");
    }

    public void itemOnAction(ActionEvent event) throws IOException {
        setForm("/view/AddItem.fxml");
    }

    public void EmployeeOnAction(ActionEvent event) throws IOException {
        setForm("/view/addEmployee.fxml");
    }

    public void SupplierOnAction(ActionEvent event) throws IOException {
        setForm("/view/AddSupplier.fxml");
    }

    public void CustomerOnAction(ActionEvent event) throws IOException {
        setForm("/view/AddCustomer.fxml");
    }

    public void CashiyerOnAction(ActionEvent event) throws IOException {
        setForm("/view/Cashier.fxml");
    }

    public void logOutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/selectroll.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();

    }

    public void SalaryOnAction(ActionEvent event) throws IOException {
        setForm("/view/Salary.fxml");
    }

    public void AttendenceOnAction(ActionEvent event) throws IOException {
        setForm("/view/Attendance.fxml");
    }
}

