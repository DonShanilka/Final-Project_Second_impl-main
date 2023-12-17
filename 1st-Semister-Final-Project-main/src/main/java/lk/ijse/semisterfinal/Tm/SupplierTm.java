package lk.ijse.semisterfinal.Tm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SupplierTm {
    private String SupId;
    private String SupName;
    private String supItemName;
    private int supqty;
    private String supMobile;

    public SupplierTm(String supId, String supName, String supItemName, String supMobile, int supqty) {
        SupId = supId;
        SupName = supName;
        this.supItemName = supItemName;
        this.supMobile = supMobile;
        this.supqty = supqty;
    }
}
