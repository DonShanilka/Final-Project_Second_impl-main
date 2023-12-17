package lk.ijse.semisterfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class IncomeDTO {
    private String order_id;
    private String item_code;
    private String qty;
    private String unit_price;
}
