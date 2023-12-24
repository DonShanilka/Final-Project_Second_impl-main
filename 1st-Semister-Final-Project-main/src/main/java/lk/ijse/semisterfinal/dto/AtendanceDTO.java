package lk.ijse.semisterfinal.dto;

import lombok.*;

@Getter
@Setter
@ToString

public class AtendanceDTO {
    private String date;
    private String employeeId;
    private String employeeName;
    private String pOra;
    private String anInt;
    private String abInt;

    public AtendanceDTO(String date, String id, String name, String pOra) {
        this.date = String.valueOf(date);
        employeeId = id;
        employeeName = name;
        this.pOra = pOra;
    }

    public AtendanceDTO(String anInt) {
        this.abInt = anInt;
    }


}
