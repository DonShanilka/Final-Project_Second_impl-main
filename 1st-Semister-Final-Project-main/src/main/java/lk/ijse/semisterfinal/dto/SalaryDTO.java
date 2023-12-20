package lk.ijse.semisterfinal.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SalaryDTO {

    private String  date;
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
    

    public SalaryDTO(double amount, String id, String name, String date1) {
        this.date = date1;
        this.employeeId = id;
        this.employeeName = name;
        this.salary = amount;
        
    }

    public SalaryDTO(double amount, String id, String name, String date1, int otHcount, double pay1h, double bonase, int epf, int etf, int prCount, int abcount, double totalsalary) {
        this.date = date1;
        this.employeeId = id;
        this.employeeName = name;
        this.salary = amount;
        this.otcount = otHcount;
        this.pay1h = pay1h;
        this.bonase = bonase;
        this.epf = epf;
        this.etf = etf;
        this.prCount = prCount;
        this.abcount = abcount;
        this.totalsalary = totalsalary;
    }

    public SalaryDTO(String string, String string1, String string2, double aDouble) {
    }
}
