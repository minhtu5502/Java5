package assignment.assignment_java5.model;

import assignment.assignment_java5.model.typeEnum.StatusOrder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Thiếu ngày tạo")
    private Date created;
    @NotNull(message = "Thiếu tổng giá")
    private BigDecimal totalPrice;
    @NotNull(message = "Thiếu trang thái đơn hàng")
    private StatusOrder statusOrder;
    @ManyToOne
    @JoinColumn
    @NotNull(message = "Thiếu người mua")
    private Users user;
}
