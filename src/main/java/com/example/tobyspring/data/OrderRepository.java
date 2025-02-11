package com.example.tobyspring.data;

import com.example.tobyspring.DataConfig;
import com.example.tobyspring.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderRepository {
    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Order order){
        // em
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // em.persist
            em.persist(order);
            em.flush();

            // transaction
            transaction.commit();

        } catch (RuntimeException e) {
            if(transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if(em.isOpen()) {
                em.close();
            }
        }
    }
}
