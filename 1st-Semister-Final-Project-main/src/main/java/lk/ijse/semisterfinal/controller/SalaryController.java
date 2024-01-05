package lk.ijse.semisterfinal.controller;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.EmployeeTm;
import lk.ijse.semisterfinal.Tm.SalaryTm;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.AtendanceDTO;
import lk.ijse.semisterfinal.dto.SalaryDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;
import lk.ijse.semisterfinal.model.CustomerModel;
import lk.ijse.semisterfinal.model.SalaryModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;


public class SalaryController implements Initializable {

    public AnchorPane root;
    public DatePicker date;
    public ComboBox<String> comEmpId;
    public TextField lblName;
    public TextArea txtMsg;
    public TableColumn <?, ?> colId;
    public TableColumn <?, ?> colName;
    public TableColumn <?, ?> colDate;
    public TableColumn <?, ?> colSalary;
    public TableColumn <?, ?> colAction;
    public TextField salary;
    public TableView<SalaryTm> salaryTm;
    public TextField txtTo;
    public TextField txtSubject;
    public Text Sending;
    public TableColumn <?,?> colOtH;
    public TableColumn <?,?> colPay1ot;
    public TableColumn <?,?> colBonase;
    public TableColumn <?,?> colEpf;
    public TableColumn <?,?> colEtf;
    public TableColumn <?,?> colPresentDay;
    public TableColumn <?,?> colAbsentDay;
    public TableColumn <?,?> colTotalSalary;
    public TextField pay1HourOt;
    public TextField txtBonase;
    public TextField txtEpf;
    public TextField txtEtf;
    public TextField absent;
    public TextField prsent;
    public TextField oTinH;

    public Label lblTotalSalary;

    private ObservableList <SalaryTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        date.setPromptText(String.valueOf(LocalDate.now()));
        loadEmployeeId();
        clearField();
        tableListener();
        setCellValueFactory();
        loadAllSalary();

    }

    private void tableListener() {
        salaryTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);

        });
    }

    private void setData(SalaryTm row) {
        date.setValue(LocalDate.parse(row.getDate()));
        comEmpId.setValue(row.getEmployeeId());
        lblName.setText(row.getEmployeeName());
        salary.setText(String.valueOf(row.getSalary()));
        oTinH.setText(String.valueOf(row.getOtcount()));
        pay1HourOt.setText(String.valueOf(row.getPay1h()));
        txtBonase.setText(String.valueOf(row.getBonase()));
        txtEpf.setText(String.valueOf(row.getEpf()));
        txtEtf.setText(String.valueOf(row.getEtf()));
        prsent.setText(String.valueOf(row.getPay1h()));
        absent.setText(String.valueOf(row.getAbcount()));
        lblTotalSalary.setText(String.valueOf(row.getTotalsalary()));

    }

    private void clearField() {
        comEmpId.setValue("");
        lblName.setText("");
        salary.setText("");

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("date"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOtH.setCellValueFactory(new PropertyValueFactory<>("otcount"));
        colPay1ot.setCellValueFactory(new PropertyValueFactory<>("pay1h"));
        colBonase.setCellValueFactory(new PropertyValueFactory<>("bonase"));
        colEpf.setCellValueFactory(new PropertyValueFactory<>("epf"));
        colEtf.setCellValueFactory(new PropertyValueFactory<>("etf"));
        colPresentDay.setCellValueFactory(new PropertyValueFactory<>("prCount"));
        colAbsentDay.setCellValueFactory(new PropertyValueFactory<>("abcount"));
        colTotalSalary.setCellValueFactory(new PropertyValueFactory<>("totalsalary"));

    }



    private void loadEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<AddEmployeeDTO> idList = AddEmployeeModel.getAllEmployee();

            for (AddEmployeeDTO dto : idList) {
                obList.add(dto.getEmployeeId());
            }

            comEmpId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comEmpIdOnAction(ActionEvent event) {
        String id = comEmpId.getValue();
        try {
            AddEmployeeDTO dto = AddEmployeeModel.searchEmployee(id);
            AtendanceDTO dto1 = SalaryModel.getABcount(id);
            AtendanceDTO dto2 = SalaryModel.getPRcount(id);
            lblName.setText(dto.getEmployeeName());
            salary.setText(String.valueOf(dto.getBasicSalary()));
            absent.setText(dto1.getAbInt());
            prsent.setText(dto2.getAbInt());
            txtTo.setText(dto.getEmail());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    private void loadAllSalary() {
        var model = new SalaryModel();

        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<SalaryDTO> dtoList = model.getAllSalary();

            for (SalaryDTO dto : dtoList) {
                obList.add(
                        new SalaryTm(
                                dto.getEmployeeId(),
                                dto.getEmployeeName(),
                                dto.getDate(),
                                dto.getSalary(),
                                dto.getOtcount(),
                                dto.getPay1h(),
                                dto.getBonase(),
                                dto.getEpf(),
                                dto.getEtf(),
                                dto.getPrCount(),
                                dto.getAbcount(),
                                dto.getTotalsalary()

                        )
                );
            }
            salaryTm.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    int lastSalary;

    public void calTotalSalary() {

        double amount = Double.parseDouble(salary.getText());
        int otHcount = Integer.parseInt(oTinH.getText());
        double pay1h = Double.parseDouble(pay1HourOt.getText());
        double bonase = Double.parseDouble(txtBonase.getText());
        int epf = Integer.parseInt(txtEpf.getText());
        int etf = Integer.parseInt(txtEtf.getText());
        int prCount = Integer.parseInt(prsent.getText());
        int abcount = Integer.parseInt(absent.getText());


        double totSalary =  (amount + bonase + (pay1h * otHcount));
        System.out.println(totSalary);

        int ep = (int) (totSalary * epf / 100);
        System.out.println(ep);

        int et = (int) (totSalary * etf / 100);
        System.out.println(et);

        if (abcount < 24) {
            System.out.println("Hi");
            lastSalary = (int) (totSalary - (ep + et));

            System.out.println("Hello");
            System.out.println("Last Salary Amount" + lastSalary);
            lblTotalSalary.setText(String.valueOf(lastSalary));

        } else if (abcount > 26) {
            System.out.println("26 +");
            int ab = abcount - 24;

            System.out.println("ab" + ab);
            double noPay = ab * (amount / 23);
            System.out.println(noPay);

            lastSalary = (int) (totSalary - ((ep + et) + (noPay)));
            System.out.println(lastSalary);
            lblTotalSalary.setText(String.valueOf(lastSalary));

        }
    }

    public void sendEmailOnAction(ActionEvent event) {

        double amount = Double.parseDouble(salary.getText());
        String id = comEmpId.getValue();
        String Name = lblName.getText();
        String date1 = String.valueOf(date.getValue());
        int otHcount = Integer.parseInt(oTinH.getText());
        double pay1h = Double.parseDouble(pay1HourOt.getText());
        double bonase = Double.parseDouble(txtBonase.getText());
        int epf = Integer.parseInt(txtEpf.getText());
        int etf = Integer.parseInt(txtEtf.getText());
        int prCount = Integer.parseInt(prsent.getText());
        int abcount = Integer.parseInt(absent.getText());
        double totalsalary = Double.parseDouble(lblTotalSalary.getText());

        var dto = new SalaryDTO(amount,id,Name,date1,otHcount,pay1h,bonase,epf,etf,prCount,abcount,totalsalary);

        try {
            boolean isaddite = SalaryModel.addSalary(dto);
            if (isaddite) {
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
                clearField();
                loadAllSalary();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        System.out.println("Start");
        Sending.setText("sending...");
        Mail mail = new Mail(); //creating an instance of Mail class
        mail.setMsg(txtMsg.getText());//email message
        mail.setTo(txtTo.getText()); //receiver's mail
        mail.setSubject(txtSubject.getText()); //email subject

        Thread thread = new Thread(mail);
        thread.start();

        System.out.println("end");
        Sending.setText("sended");

        calTotalSalary();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setPromptText(String.valueOf(LocalDate.now()));
        loadEmployeeId();
        clearField();
        setCellValueFactory();
        loadAllSalary();
        tableListener();

    }

    public void calSalaryOnAction(ActionEvent actionEvent) {

        calTotalSalary();

        //txtTotalSalary.setText("Rs : " + total);
        //(String.valueOf
    }

    public void salaryDeleteOnAction(ActionEvent actionEvent) {

        String id = lblName.getText();

        try{
            boolean isDelete = SalaryModel.deleteSalary(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted").show();
                loadAllSalary();
                clearField();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public class Mail implements Runnable {
        private String msg;
        private String to;
        private String subject;

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public boolean outMail() throws MessagingException {
            String from = "nshanilka999@gmail.com"; //sender's email address
            String host = "localhost";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("nshanilka999@gmail.com", "bnsy wdyx uyop fbrc");  // email and password
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(this.subject);
            mimeMessage.setText(this.msg);
            Transport.send(mimeMessage);
            return true;
        }

        public void run() {
            if (msg != null) {
                try {
                    outMail();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("not sent. empty msg!");
            }
        }
    }
}



