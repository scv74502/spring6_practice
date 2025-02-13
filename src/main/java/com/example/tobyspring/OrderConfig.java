package com.example.tobyspring;

import com.example.tobyspring.data.JpaOrderRepository;
import com.example.tobyspring.order.OrderRepository;
import com.example.tobyspring.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
// DataConfig 설정들도 모두 가져옴
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderService orderService(JpaTransactionManager transactionManager){
        return new OrderService(orderRepository(), transactionManager);
    }

    @Bean
    public OrderRepository orderRepository(){
        return new JpaOrderRepository();
    }
}
