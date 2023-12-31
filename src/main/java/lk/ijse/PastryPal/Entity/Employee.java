package lk.ijse.PastryPal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private String employee_id;
    private String first_name;
    private String last_name;
    private String address;
    private String phone_number;
}
