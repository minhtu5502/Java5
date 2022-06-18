package assignment.assignment_java5.model;

import assignment.assignment_java5.model.typeEnum.Gender;
import assignment.assignment_java5.model.typeEnum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Nationalized
    @NotNull(message = "Thiếu tên")
    private String fullName;
    @NotNull(message = "Thiếu tên đăng nhâp")
    private String username;
    @NotNull(message = "Thiếu mật khẩu")
    private String password;
    @NotNull(message = "Thiếu email")
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotNull(message = "Thiếu số điện thoại")
    @Min(value = 10, message = "Số điện thoại phải từ 10 kí tự trở lên")
    private String phone;
    @NotNull(message = "Thiếu giới tính")
    private Gender gender;
    private Role role;
    private boolean status;
}
