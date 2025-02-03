package com.example.tobyspring.payment;

import jakarta.annotation.Nonnull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
//    @DisplayName("prepare 메소드가 요구사항 3가지 잘 충족하는지 검증하기")
    void convertedAmount() {
        testAmount(valueOf(500), valueOf(5_000), this.clock);
        testAmount(valueOf(1000), valueOf(10_000), this.clock);
        testAmount(valueOf(3000), valueOf(30_000), this.clock);

        // 원화환산금액의 유효시간 계산
//        assertThat(payment.getValidUntil())
//                .isAfter(LocalDateTime.now());  // 정확히 어떤 값을 현재 시간에 더했는지는 모름, 현재보다 이후인지만 검증
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));  // 현재 시간보다 30분 뒤 시점보다 이전인지 검증
    }

    @Test
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidateUntil = now.plusMinutes(30);

        assertThat(payment.getValidUntil()).isEqualTo(expectedValidateUntil);
    }

    private static Payment testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);


//        assertThat(payment.getExRate()).isNotNull(); // 값이 들어가 있는지 검증하는 과거 테스트
        // 환율정보 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate); // 값이 ExRateProviderStub의 값과 같은지 검증

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
        return payment;
    }
}








