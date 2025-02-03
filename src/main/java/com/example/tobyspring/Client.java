package com.example.tobyspring;

import com.example.tobyspring.payment.Payment;
import com.example.tobyspring.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

//        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
//        System.out.println("Payment 1 : " + payment1);
//
//        System.out.println("--------------------------------------------------------------------------------");
//
//        TimeUnit.SECONDS.sleep(1);;
//
//        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
//        System.out.println("Payment 2 : " + payment2);
//
//        System.out.println("--------------------------------------------------------------------------------");
//
//        TimeUnit.SECONDS.sleep(2);;
//
//
//        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
//        System.out.println("Payment 3 : " + payment3);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("Payment : " + payment);
    }
}
