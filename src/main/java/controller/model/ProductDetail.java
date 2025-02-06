package controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDetail {
    private Integer prodCode;
    private Integer supId;
    private Double unitPrice;
    private Integer qtySupplied;
}
