package assignment.assignment_java5.service;


import assignment.assignment_java5.model.Orders;
import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.StatusOrder;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderService {
    Orders addOrder(Users user, Date created, BigDecimal totalPrice);

    Orders getOrderDesc();

    List<Orders> getAllOrders(Users user);
    List<Orders> getAllOrdersByStatus(StatusOrder status);
    Page<Orders> getAllOrders(int pageNumber, int maxRecords);

    Orders updateStatus(Integer id, StatusOrder status);

}
