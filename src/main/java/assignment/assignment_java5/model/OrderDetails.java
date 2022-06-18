package assignment.assignment_java5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Thiếu số lượng mua")
    private Integer quantity;
    @NotNull(message = "Thiếu giá mua")
    private BigDecimal price;
    @ManyToOne
    @JoinColumn
    @NotNull(message = "Thiếu hoá đơn ")
    private Orders order;
    @ManyToOne
    @JoinColumn
    @NotNull(message = "Thiếu sản phẩm")
    private AgriculturalProduce agriculturalProduce;
}
