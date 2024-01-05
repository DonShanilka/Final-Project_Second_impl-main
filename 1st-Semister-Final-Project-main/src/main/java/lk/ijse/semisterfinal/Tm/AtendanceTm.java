package lk.ijse.semisterfinal.Tm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AtendanceTm {
    private String date;
    private String employeeId;
    private String employeeName;
    private String Present;

    /*public AtendanceTm(String employeeId, String employeeName, String date, String present) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.Present = present;
    }*/

}
