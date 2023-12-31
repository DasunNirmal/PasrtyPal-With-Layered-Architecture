package lk.ijse.PastryPal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Complain {
    private String complain_id;
    private String description;
    private LocalDate complain_date;
}
