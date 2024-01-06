package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.AtendanceTm;
import lk.ijse.semisterfinal.Tm.EmployeeTm;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.AtendanceDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;
import lk.ijse.semisterfinal.model.AtendanceModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class AttendanceController implements Initializable {

    public AnchorPane root;

    public DatePicker date;
    public ComboBox comEmpId;
    public TextField lblName;
    public TableColumn <?,?> colId;
    public TableColumn <?,?> colName;
    public TableColumn <?,?> colDate;
    public TableColumn <?,?> colAction;
    public Label newCustomer;
    public DatePicker AttDate;
    public ChoiceBox <String> presentAbsent;
    public TableColumn <?,?> colPa;
    public TableView <AtendanceTm> atendanceTM;
    private AddEmployeeModel employeeModel = new AddEmployeeModel();
    private AtendanceModel attendanceModel = new AtendanceModel();
    //private ObservableList<AtendanceTm> obList = FXCollections.observableArrayList();

    private String[] pA = {"Present" , "Absent"};

    public void initialize() {
        AttDate.setPromptText(String.valueOf(LocalDate.now()));
        loadAllEmployee();
        setCellValueFactory();
        loadallAttendance();

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPa.setCellValueFactory(new PropertyValueFactory<>("Present"));
    }

    private void tableListener() {
        atendanceTM.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);

        });
    }

    private void setData(AtendanceTm row) {
        comEmpId.setValue(row.getEmployeeId());
        lblName.setText(row.getEmployeeName());
        date.setValue(LocalDate.parse(row.getDate()));
        presentAbsent.setValue(row.getPresent());

    }

    private void loadAllEmployee() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<AddEmployeeDTO> teacherDtos = AddEmployeeModel.getAllEmployee();

            for (AddEmployeeDTO dto : teacherDtos) {
                obList.add(dto.getEmployeeId());
            }
            comEmpId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void markAtendanseOnAction(ActionEvent event) {
        String date = String.valueOf(AttDate.getValue());
        String id = String.valueOf(comEmpId.getValue());
        String name = lblName.getText();
        String pOra = presentAbsent.getValue();

        var dto = new AtendanceDTO(date,id,name,pOra);

        try {
            boolean isaddite = AtendanceModel.addAttendance(dto);
            if (isaddite) {
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
                loadallAttendance();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public void cmbEmpIdOnAction(ActionEvent event) {
        String id = (String) comEmpId.getValue();
        try {
            AddEmployeeDTO dto = AddEmployeeModel.searchEmployee(id);
            lblName.setText(dto.getEmployeeName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void loadallAttendance(){
        //var model = new AtendanceModel();

        ObservableList<AtendanceTm>  oblist = FXCollections.observableArrayList();

        try{
            List<AtendanceDTO> dtoList = AtendanceModel.getAllatendance();

            for (AtendanceDTO dto: dtoList) {
                oblist.add(new AtendanceTm(
                        dto.getDate(),
                        dto.getEmployeeId(),
                        dto.getEmployeeName(),
                        dto.getPOra()
                ));
            }
            atendanceTM.setItems(oblist);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void BackOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AttDate.setPromptText(String.valueOf(LocalDate.now()));
        loadAllEmployee();
        setCellValueFactory();
        presentAbsent.getItems().addAll(pA);
        loadallAttendance();
        //tableListener();
    }
}
