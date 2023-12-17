package lk.ijse.semisterfinal.Tm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class AtendanceTm {
    private String employeeId;
    private String employeeName;
    private String date;
    private String Present;

    public AtendanceTm(String employeeId, String employeeName, String date, String present) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.Present = present;
    }

}
