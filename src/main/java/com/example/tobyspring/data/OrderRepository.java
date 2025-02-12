package com.example.tobyspring.data;

import com.example.tobyspring.DataConfig;
import com.example.tobyspring.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Order order){
        entityManager.persist(order);
    }
}
