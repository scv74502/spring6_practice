package com.example.tobyspring.order;

import com.example.tobyspring.OrderConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {
    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;

    @Test
    void createOrder(){
        var order = orderService.createOrder("0100", BigDecimal.ONE);

        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders(){
        List<OrderReq> reqs = List.of(
                new OrderReq("0200", BigDecimal.ONE),
                new OrderReq("0201", BigDecimal.TWO)
        );

        var orders = orderService.createOrders(reqs);

        assertThat(orders).hasSize(2);
        orders.forEach(order -> {
            assertThat(order.getId()).isGreaterThan(0);
        });
    }

    @Test
    void createDuplicatedOrders(){
        List<OrderReq> reqs = List.of(
                new OrderReq("0300", BigDecimal.ONE),
                new OrderReq("0300", BigDecimal.TWO)
        );

        assertThatThrownBy(() -> orderService.createOrders(reqs))
                .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        Long count = client.sql("select count(*) from orders where no = '0300'")
                .query(Long.class)
                .single();

        assertThat(count).isEqualTo(0);
    }
}
