package lk.ijse.semisterfinal.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class ItemDTO {
    private String ItemCode;
    private String itemDetails;
    private double ItemPrice;
    private String SupplierId;
    private String WarrantyPeriod;
    private int ItemQty;

    public ItemDTO(String text, String text1, Object value) {

    }

    public ItemDTO(String itemCode, String itemName, double itemPrice, String supId, String warranty, String qty) {
        ItemCode = itemCode;
        itemDetails = itemName;
        ItemPrice = itemPrice;
        SupplierId = supId;
        WarrantyPeriod = warranty;
        ItemQty = Integer.parseInt(qty);
    }
}
