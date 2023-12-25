package lk.ijse.semisterfinal.Tm;


import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import lombok.*;

@Getter
@Setter
@ToString

public class SalaryTm {
    private String date;
    private String employeeId;
    private String employeeName;
    private double salary;
    private int otcount;
    private double pay1h;
    private double bonase;
    private int epf;
    private int etf;
    private int prCount;
    private int abcount;
    private double totalsalary;
    private double lblTotalSalary;

    public SalaryTm(String date, String employeeId, String employeeName, double salary, int otcount, double pay1h, double bonase, int epf, int etf, int prCount, int abcount, double totalsalary) {
        this.date = date;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.otcount = otcount;
        this.pay1h = pay1h;
        this.bonase = bonase;
        this.epf = epf;
        this.etf = etf;
        this.prCount = prCount;
        this.abcount = abcount;
        this.totalsalary = totalsalary;
    }

    public SalaryTm(double lblTotalSalary) {
        this.lblTotalSalary = lblTotalSalary;
    }


    public static ChoiceDialog<Object> getSelectionModel() {
        ChoiceDialog<Object> choiceDialog = new ChoiceDialog<>();
        choiceDialog.setTitle("Delete");
        choiceDialog.setHeaderText("Are you sure you want to delete this item?");
        return choiceDialog;
    }

}
