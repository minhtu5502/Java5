package assignment.assignment_java5.model;

import assignment.assignment_java5.model.typeEnum.StatusAgriculturalProduce;
import assignment.assignment_java5.model.typeEnum.TypeAgriculturalProduce;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgriculturalProduce//Nông sản
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Nationalized
    @NotNull(message = "Thiếu tên")
    private String name;
    @NotNull(message = "Thiếu ảnh")
    private String image;
    @NotNull(message = "Thiếu trọng lượng")
    private double weight;//Trọng Lượng
    @ManyToOne
    @JoinColumn
    @NotNull(message = "Thiếu nhà cung cấp")
    private Supplier supplier;
    @Nationalized
    @NotNull(message = "Thiếu giá trị dinh dưỡng")
    private String nutrition;//Dinh dưỡng
    @NotNull(message = "Thiếu giá")
    private BigDecimal price;
    @NotNull(message = "Thiếu số lượng")
    private int quantity;
    @NotNull(message = "Thiếu ngày tạo")
    private Date createdDate;
    @NotNull(message = "Thiếu loại")
    private TypeAgriculturalProduce type;
    @NotNull(message = "Thiếu trạng thái")
    private StatusAgriculturalProduce status;


}
