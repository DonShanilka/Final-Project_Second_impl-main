package lk.ijse.semisterfinal.Tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class EmployeeTm {
    private String id;
    private String name;
    private String mobile;
    private String address;
    private String date;
    private String possition;
    private String salary;
    private String email;
    private String Gender;
    private String Education;
    private double BasicSalary;
    private String Expiriance;


    public EmployeeTm(String employeeId, String employeeName, String empAddress, int employeePhone, String empDate, String empPosition, String email, String gender, String education, double basicSalary, String expiriance) {
        this.id = employeeId;
        this.name = employeeName;
        this.address = empAddress;
        this.mobile = String.valueOf(employeePhone);
        this.date = empDate;
        this.email = email;
        this.possition = empPosition;
        this.Gender = gender;
        this.Education = education;
        this.BasicSalary = basicSalary;
        this.Expiriance = expiriance;
    }
}
