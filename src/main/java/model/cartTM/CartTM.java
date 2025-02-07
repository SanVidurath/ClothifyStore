package model.cartTM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartTM {
    private Integer productCode;
    private String productDescription;
    private Double unitPrice;
    private Integer quantity;
    private Double total;
}
