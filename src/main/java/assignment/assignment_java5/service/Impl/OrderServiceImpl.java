package assignment.assignment_java5.service.Impl;

import assignment.assignment_java5.model.Orders;
import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.StatusOrder;
import assignment.assignment_java5.repository.IOrderRepository;
import assignment.assignment_java5.repository.IUsersRepository;
import assignment.assignment_java5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IUsersRepository usersRepository;

    @Override
    public Orders addOrder(Users user, Date created, BigDecimal totalPrice) {
        Orders orders = new Orders();
        orders.setId(null);
        orders.setUser(user);
        orders.setCreated(new Date());
        orders.setTotalPrice(totalPrice);
        orders.setStatusOrder(StatusOrder.a);
        return orderRepository.save(orders);
    }

    @Override
    public Orders getOrderDesc() {
        return orderRepository.findTop1ByOrderByIdDesc();
    }

    @Override
    public List<Orders> getAllOrders(Users user) {
        return orderRepository.findByUserOrderByIdDesc(user);
    }

    @Override
    public List<Orders> getAllOrdersByStatus(StatusOrder status) {
        return orderRepository.findByStatusOrderOrderByIdDesc(status);
    }

    @Override
    public Page<Orders> getAllOrders(int pageNumber, int maxRecords) {
        Pageable pageable = PageRequest.of(pageNumber - 1, maxRecords);
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public Orders updateStatus(Integer id, StatusOrder status) {
        Optional<Orders> orders = this.orderRepository.findById(id);
        if (orders.isPresent()) {
            Orders orderUpdate = orders.get();
            orderUpdate.setStatusOrder(status);
            return orderRepository.save(orderUpdate);
        }
        return null;
    }
}
