package lk.ijse.semisterfinal.dto;

import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@ToString

public class AddEmployeeDTO {
    private String employeeId;
    private String EmployeeName;
    private String EmpAddress;
    private int EmployeePhone;
    private String empDate;
    private String empPosition;
    private String email;
    private String Gender;
    private String Education;
    private double BasicSalary;
    private String Expiriance;

    public AddEmployeeDTO(String id, String name, String address, int tele, String date, String email, String position) {
        this.employeeId = id;
        this.EmployeeName = name;
        this.EmpAddress = address;
        this.EmployeePhone = tele;
        this.empDate = date;
        this.email = email;
        this.empPosition = position;
    }

    public AddEmployeeDTO(String id, String name, String address, int tele, String date, String email, String position, String gender, String education, double basic, String experiance) {
        this.employeeId = id;
        this.EmployeeName = name;
        this.EmpAddress = address;
        this.EmployeePhone = tele;
        this.empDate = date;
        this.empPosition = position;
        this.email = email;
        this.Gender = gender;
        this.Education = education;
        this.BasicSalary = basic;
        this.Expiriance = experiance;

    }

}
