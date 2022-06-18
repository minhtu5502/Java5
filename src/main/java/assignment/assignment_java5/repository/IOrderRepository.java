package assignment.assignment_java5.repository;

import assignment.assignment_java5.model.Orders;
import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IOrderRepository extends JpaRepository<Orders, Integer> {
    Orders findTop1ByOrderByIdDesc();

    List<Orders> findByUserOrderByIdDesc(Users user);
    List<Orders> findByStatusOrderOrderByIdDesc(StatusOrder statusOrder);
}
