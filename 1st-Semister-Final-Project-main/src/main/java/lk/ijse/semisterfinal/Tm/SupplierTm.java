package lk.ijse.semisterfinal.Tm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class SupplierTm {
    private String supId;
    private String supName;
    private int mobile;
    private String email;
    private String coName;
    private String coAddress;
    private int itemcode;
    private String itemName;
    private int qty;
    private String bNum;
    private String catagory;

    public SupplierTm(String supId, String supName, int mobile, String email, String coName, String coAddress, int itemcode, String itemName, int qty, String bNum, String catagory) {
        this.supId = supId;
        this.supName = supName;
        this.mobile = mobile;
        this.email = email;
        this.coName = coName;
        this.coAddress = coAddress;
        this.itemcode = itemcode;
        this.itemName = itemName;
        this.qty = qty;
        this.bNum = bNum;
        this.catagory = catagory;

    }
}
