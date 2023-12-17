package lk.ijse.semisterfinal.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class AtendanceDTO {
    private String date;
    private String employeeId;
    private String employeeName;
    private String pOra;

    public AtendanceDTO(String date, String id, String name, String pOra) {
        this.date = String.valueOf(date);
        employeeId = id;
        employeeName = name;
        this.pOra = pOra;
    }
}
