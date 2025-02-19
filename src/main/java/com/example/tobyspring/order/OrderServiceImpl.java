package com.example.tobyspring.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository OrderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.OrderRepository = orderRepository;
    }

    @Override
    public Order createOrder(String no, BigDecimal total){
        Order order = new Order(no, total);

//        return new TransactionTemplate(transactionManager).execute(status -> {
            this.OrderRepository.save(order);
            return order;
//        });
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs){
        return reqs.stream().map(orderReq -> createOrder(orderReq.no(), orderReq.total())).toList();
    }
}
