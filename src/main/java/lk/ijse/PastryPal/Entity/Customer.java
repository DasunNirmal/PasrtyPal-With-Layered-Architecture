package lk.ijse.PastryPal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String customer_id;
    private String name;
    private String address;
    private String phone_number;
}