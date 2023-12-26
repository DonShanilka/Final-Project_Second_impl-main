package lk.ijse.semisterfinal.controller;

import com.google.zxing.WriterException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.EmployeeTm;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    public TextField txtemployeeId;
    public TextField txtEmployeeName;
    public TextField txtEmployeePhone;
    public TextField txtAddress;
    public DatePicker empDate;
    public TextField txtPossition;
    public TableColumn <?, ?>  tmid;
    public TableColumn <?, ?>  tmEmpName;
    public TableColumn <?, ?>  tmEmpGender;
    public TableColumn <?, ?>  tmEmpMobile;
    public TableColumn <?, ?>  tmEmpAddress;
    public TableColumn <?, ?>  tmStartDate;
    public TableColumn <?, ?>  tmEmpPossition;
    public TableView <EmployeeTm>  EmployeeTm;
    public AnchorPane root;
    public Label lblTotalEmployee;
    public TextField txtEmail;
    public TableColumn <?, ?> tmEmpJob;
    public TableColumn <?, ?> tmEmpEmail;
    public TextField txtGender;
    public TextField txtBasicSalary;
    public TextField txtExpiriance;
    public TableColumn<?,?> tmGender;
    public TableColumn <?,?> tmQualification;
    public TableColumn <?,?> tmExperiance;
    public TableColumn <?,?> tmBasicSalary;
    public ChoiceBox <String> gender;
    public ChoiceBox <String> department;
    public TableColumn tmDepartment;
    public ChoiceBox <String> txtEducation;

    private String[] mf = {"Male" , "Female"};
    private String[] dep = {"HR", "Finance & Accounting", "Service", "IT"};
    private String[] edu = {"O/L", "A/L", "Diploma", "HND", "Degree", "Masters"};

    public void initialize(){
        loadAllEmployee();
        clearField();
        setCellValueFactory();
        tableListener();

    }

    private void setCellValueFactory() {
        tmid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tmEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tmEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tmEmpMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tmStartDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tmEmpJob.setCellValueFactory(new PropertyValueFactory<>("email"));
        tmEmpEmail.setCellValueFactory(new PropertyValueFactory<>("possition"));
        tmGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        tmQualification.setCellValueFactory(new PropertyValueFactory<>("Education"));
        tmBasicSalary.setCellValueFactory(new PropertyValueFactory<>("BasicSalary"));
        tmExperiance.setCellValueFactory(new PropertyValueFactory<>("Expiriance"));
        tmDepartment.setCellValueFactory(new PropertyValueFactory<>("de"));

    }

    private void clearField() {
        txtemployeeId.setText("");
        txtEmployeeName.setText("");
        txtAddress.setText("");
        txtEmployeePhone.setText("");
        txtEmail.setText("");
        txtPossition.setText("");
        txtEducation.setValue("");
        txtBasicSalary.setText("");
        txtExpiriance.setText("");

    }

    private void tableListener() {
        EmployeeTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(EmployeeTm row) {
        txtemployeeId.setText(row.getId());
        txtEmployeeName.setText(row.getName());
        txtAddress.setText(row.getAddress());
        txtEmployeePhone.setText(String.valueOf(row.getMobile()));
        txtPossition.setText(String.valueOf(row.getEmail()));
        txtEmail.setText(String.valueOf(row.getPossition()));
        empDate.setValue(LocalDate.parse(row.getDate()));
        gender.setValue(row.getGender());
        txtEducation.setValue(row.getEducation());
        txtBasicSalary.setText(String.valueOf(row.getBasicSalary()));
        txtExpiriance.setText(row.getExpiriance());
        department.setValue(String.valueOf(row.getDe()));

    }

    public void EmployeeAddOnAction(ActionEvent event) {
        String id = txtemployeeId.getText();
        String name = txtEmployeeName.getText();
        String address = txtAddress.getText();
        int tele = Integer.parseInt(txtEmployeePhone.getText());
        String date = String.valueOf(empDate.getValue());
        String email = txtEmail.getText();
        String position = txtPossition.getText();
        String gende = gender.getValue();
        String education = txtEducation.getValue();
        double basic = Double.parseDouble(txtBasicSalary.getText());
        String experiance = txtExpiriance.getText();
        String de = department.getValue();

        var dto = new AddEmployeeDTO(id,name,address,tele,date,email,position,gende,education,basic,experiance,de);

        try {
            boolean addSup= AddEmployeeModel.addEmployee(dto);
            if (addSup) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee is Added").show();
                loadAllEmployee();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void EmployeeUpdateOnAction(ActionEvent event) throws IOException {
        String id = txtemployeeId.getText();
        String name = txtEmployeeName.getText();
        String address = txtAddress.getText();
        int tele = Integer.parseInt(txtEmployeePhone.getText());
        String date = String.valueOf(empDate.getValue());
        String position = txtPossition.getText();
        String email = txtEmail.getText();
        String gende = gender.getValue();
        String education = txtEducation.getValue();
        double basic = Double.parseDouble(txtBasicSalary.getText());
        String experiance = txtExpiriance.getText();
        String de = department.getValue();

        try{
            /*if (!validateEmployee()){
                return;
            }*/
            var dto = new AddEmployeeDTO(id,name,address,tele,date,email,position,gende,education,basic,experiance,de);
            boolean isUpdate = AddEmployeeModel.updateEmployee(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee is updated").show();
                loadAllEmployee();
                clearField();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void EmployeeDeleteOnAction(ActionEvent event) {
        String id = txtemployeeId.getText();

        try {
            boolean isDeleted = AddEmployeeModel.deleteEmployee(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier has deleted!").show();
                loadAllEmployee();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllEmployee() {
        var model = new AddEmployeeModel();

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<AddEmployeeDTO> dtoList = model.getAllEmployee();

            for (AddEmployeeDTO dto : dtoList) {
                obList.add(
                        new EmployeeTm(
                                dto.getEmployeeId(),
                                dto.getEmployeeName(),
                                dto.getEmpAddress(),
                                dto.getEmployeePhone(),
                                dto.getEmpDate(),
                                dto.getEmpPosition(),
                                dto.getEmail(),
                                dto.getGender(),
                                dto.getEducation(),
                                dto.getBasicSalary(),
                                dto.getExpiriance(),
                                dto.getDe()
                        )
                );
            }

            EmployeeTm.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void totalItem() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT COUNT(employee_id) FROM employee";

        String totalid = null;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalid = resultSet.getString("COUNT(employee_id)");
            }
            lblTotalEmployee.setText(totalid);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            department.getItems().addAll(dep);
            gender.getItems().addAll(mf);
            totalItem();
            loadAllEmployee();
            clearField();
            setCellValueFactory();
            tableListener();
            txtEducation.getItems().addAll(edu);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void EmployeeSalaryViewOnAction(ActionEvent actionEvent) throws WriterException, SQLException {
        String values = txtemployeeId.getText() + "," + txtEmployeeName.getText() + "," + txtPossition + "," + gender.getValue() + "," +txtEducation.getValue() + "," + txtAddress.getText() + "," + txtEmail.getText();//QR code ekata watenna oone details tika..

        String filepath = "C:\\Users\\Shanilka\\Documents\\QR"+ "qr"+ txtemployeeId.getText() +".png"; //Save wenna oone folder eke path eka..
        boolean isGenerated = QR.generateQrCode(values, 1250, 1250, filepath);

        if (isGenerated){
            new Alert(Alert.AlertType.CONFIRMATION, "Generated QR Code").show();
        } else {
            throw new SQLException();
        }
    }
}
