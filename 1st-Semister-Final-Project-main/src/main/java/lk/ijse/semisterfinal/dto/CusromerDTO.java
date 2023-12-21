package lk.ijse.semisterfinal.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class CusromerDTO {
    private String txtCustId;
    private String txtCustAddress;
    private String txtCustName;
    private String txtCustMobile;
    private String txtCustitemId;
    private String txtCustPayment;

    public CusromerDTO(String custId, String custAddress, String custName, String custMobile, String payment) {
        txtCustId = custId;
        txtCustAddress = custAddress;
        txtCustName = custName;
        txtCustMobile = custMobile;
        txtCustPayment = payment;
    }

    public String getTxtCustName() {
        return txtCustName;
    }

    public void setTxtCustName(String txtCustName) {
        this.txtCustName = txtCustName;
    }
}
