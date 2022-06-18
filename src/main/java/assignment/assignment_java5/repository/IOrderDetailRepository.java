package assignment.assignment_java5.repository;

import assignment.assignment_java5.model.OrderDetails;
import assignment.assignment_java5.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IOrderDetailRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByOrder(Orders order);
}
