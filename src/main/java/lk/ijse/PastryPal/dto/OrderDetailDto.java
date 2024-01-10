package lk.ijse.PastryPal.dto;

import lk.ijse.PastryPal.dto.tm.OrderTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailDto {

    private String order_id;
    private List<OrderTm> orderTmList = new ArrayList<>();
}
