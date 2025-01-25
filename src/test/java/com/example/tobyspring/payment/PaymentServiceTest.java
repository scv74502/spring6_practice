package com.example.tobyspring.payment;

import com.example.tobyspring.exrate.WebApiExRateProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {
    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지 잘 충족하는지 검증하기")
    void prepare() throws IOException {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보 가져온다
        assertThat(payment.getExRate()).isNotNull(); // 값이 들어가 있는지 검증

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount())
                .isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // 원화환산금액의 유효시간 계산
        assertThat(payment.getValidUntil())
                .isAfter(LocalDateTime.now());  // 정확히 어떤 값을 현재 시간에 더했는지는 모름, 현재보다 이후인지만 검증
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));  // 현재 시간보다 30분 뒤 시점보다 이전인지 검증
    }
}