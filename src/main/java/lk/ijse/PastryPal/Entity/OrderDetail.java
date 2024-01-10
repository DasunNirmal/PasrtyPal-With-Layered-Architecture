package lk.ijse.PastryPal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetail {
    private String order_id;
    private String product_id;
    private int qty;
    private double unit_price;
}
