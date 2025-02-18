package com.example.tobyspring.order;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository OrderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        this.OrderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total){
        Order order = new Order(no, total);

//        return new TransactionTemplate(transactionManager).execute(status -> {
            this.OrderRepository.save(order);
            return order;
//        });
    }

    public List<Order> createOrders(List<OrderReq> reqs){
        return new TransactionTemplate(transactionManager).execute(status ->
            reqs.stream().map(orderReq -> createOrder(orderReq.no(), orderReq.total())).toList()
        );
    }
}
