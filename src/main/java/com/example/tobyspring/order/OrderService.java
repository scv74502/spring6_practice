package com.example.tobyspring.order;

import com.example.tobyspring.data.JpaOrderRepository;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
public class OrderService {
    private final OrderRepository OrderRepository;
    private final JpaTransactionManager transactionManager;

    public OrderService(OrderRepository orderRepository, JpaTransactionManager transactionManager) {
        this.OrderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total){
        Order order = new Order(no, total);

        return new TransactionTemplate(transactionManager).execute(status -> {
            this.OrderRepository.save(order);
            return order;
        });
    }
}
