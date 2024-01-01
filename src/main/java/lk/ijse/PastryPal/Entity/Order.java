package lk.ijse.PastryPal.Entity;

import lk.ijse.PastryPal.dto.tm.OrderTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private String order_id;
    private LocalDate date;
    private String customer_id;
}
