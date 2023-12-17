package lk.ijse.semisterfinal.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private String orderId;
    private LocalDate date;
    private String customerId;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private String billId;
    private String itemId;
    private String itemName;
    private double itemPrice;
    private double total;
    private double discount;
    private double payment;
    private double balance;

    public BillDTO(String billId, String itemId, String itemName, double itemPrice, LocalDate date,int qty,double discount,double total,double payment,double balance) {
        this.orderId = billId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.date = date;
        this.qty = qty;
        this.discount = discount;
        this.total = total;
        this.payment = payment;
        this.balance = balance;
    }
}
