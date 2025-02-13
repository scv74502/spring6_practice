package com.example.tobyspring;

import com.example.tobyspring.data.OrderRepository;
import com.example.tobyspring.order.Order;
import com.example.tobyspring.order.OrderService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        OrderService service = beanFactory.getBean(OrderService.class);
        
//        try{
//            new TransactionTemplate(transactionManager).execute((TransactionCallback<Order>) status -> {
//                Order order = new Order("100", BigDecimal.TEN);
//                repository.save(order);
//
//                System.out.println(order);
//
//                Order order2 = new Order("100", BigDecimal.TEN);
//                repository.save(order2);
//
//                return null;
//            });
//        } catch (DataIntegrityViolationException e){
//            System.out.println("주문번호 충돌 복구 작업");
//        }

        Order order = service.createOrder("0100", BigDecimal.TEN);
        System.out.println(order);
    }
}
