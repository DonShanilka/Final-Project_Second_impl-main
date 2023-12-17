package lk.ijse.semisterfinal.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class CusromerDTO {
    private String txtCustId;
    private String txtCustName;
    private String txtCustAddress;
    private String txtCustMobile;
    private String txtCustitemId;
    private String txtCustPayment;




    public CusromerDTO(String custId, String custAddress, String custName, String custMobile, String payment) {
        txtCustId = custId;
        txtCustName = custName;
        txtCustAddress = custAddress;
        txtCustMobile = custMobile;
        txtCustPayment = payment;
    }
}
