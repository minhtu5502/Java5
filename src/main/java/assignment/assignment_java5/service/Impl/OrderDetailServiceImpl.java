package assignment.assignment_java5.service.Impl;

import assignment.assignment_java5.model.AgriculturalProduce;
import assignment.assignment_java5.model.OrderDetails;
import assignment.assignment_java5.model.Orders;
import assignment.assignment_java5.repository.IAgriculturalProduceRepository;
import assignment.assignment_java5.repository.IOrderDetailRepository;
import assignment.assignment_java5.repository.IOrderRepository;
import assignment.assignment_java5.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailsService {

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Autowired
    private IAgriculturalProduceRepository agriculturalProduceRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public OrderDetails addOrderDetails(Integer agriculturalProduceId, BigDecimal price, Integer quantity) {
        OrderDetails orderDetails = new OrderDetails();
        AgriculturalProduce agriculturalProduce = agriculturalProduceRepository.findById(agriculturalProduceId).get();
        agriculturalProduce.setQuantity(agriculturalProduce.getQuantity() - quantity);
        Orders orders = orderRepository.findTop1ByOrderByIdDesc();
        orderDetails.setAgriculturalProduce(agriculturalProduce);
        orderDetails.setOrder(orders);
        orderDetails.setPrice(price);
        orderDetails.setQuantity(quantity);

        return orderDetailRepository.save(orderDetails);
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetails> getById(Integer id) {
        Optional<Orders> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return orderDetailRepository.findByOrder(order.get());
        }
        return null;
    }
}
