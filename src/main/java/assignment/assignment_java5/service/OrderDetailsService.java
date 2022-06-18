package assignment.assignment_java5.service;



import assignment.assignment_java5.model.OrderDetails;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailsService {

    OrderDetails addOrderDetails(Integer agriculturalProduceId, BigDecimal price, Integer quantity);

    List<OrderDetails> getAllOrderDetails();

    List<OrderDetails> getById(Integer id);

}
