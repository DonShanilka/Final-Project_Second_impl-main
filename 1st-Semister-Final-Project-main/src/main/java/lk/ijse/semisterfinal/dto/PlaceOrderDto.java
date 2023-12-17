package lk.ijse.semisterfinal.dto;

import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.semisterfinal.Tm.CartTm;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class PlaceOrderDto {
    private String orderId;
    private LocalDate date;
    private String customerId;
    private String itemCode;
    private TextField qty;
    private double unitPrice;
    private List<CartTm> cartTmList = new ArrayList<>();

    private String billId;
    private String itemId;
    private String itemName;
    private Label itemPrice;
    private Label total;
    private TextField discount;
    private TextField payment;
    private Label balance;

    public PlaceOrderDto(String billId, JFXComboBox<String> itemId, Label itemName, Label itemPrice, LocalDate date, TextField qty, TextField discount, Label total, TextField payment, Label balance) {
        this.orderId = billId;
        this.itemId = String.valueOf(itemId);
        this.itemName = String.valueOf(itemName);
        this.itemPrice = itemPrice;
        this.date = date;
        this.qty = qty;
        this.discount = discount;
        this.total = total;
        this.payment = payment;
        this.balance = balance;
    }

    public PlaceOrderDto(String orderId, LocalDate date, String customerId, List<CartTm> cartTmList) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.cartTmList = cartTmList;
    }







    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCartTmList(List<CartTm> cartTmList) {
        this.cartTmList = cartTmList;
    }

    @Override
    public String toString() {
        return "PlaceOrderDto{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                ", cartTmList=" + cartTmList +
                '}';
    }
}
