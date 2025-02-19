package com.example.tobyspring;

import com.example.tobyspring.data.JpaOrderRepository;
import com.example.tobyspring.order.Order;
import com.example.tobyspring.order.OrderServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        JpaOrderRepository repository = beanFactory.getBean(JpaOrderRepository.class);
        OrderServiceImpl service = beanFactory.getBean(OrderServiceImpl.class);
        
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
