package assignment.assignment_java5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetails {
    private AgriculturalProduce agriculturalProduce;
    private int quantityOrder;

}
