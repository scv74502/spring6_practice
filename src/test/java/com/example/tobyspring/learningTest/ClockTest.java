package com.example.tobyspring.learningTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class ClockTest {
    // Clock을 이용해서 LocalDateTime.now? 테스트
    // Clock을 Test에서 사용 시 내가 원하는 시간을 지정하여 현재 시간을 가져오게 할 수 있는지
    @Test
    void clock() throws InterruptedException {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);
        assertThat(dt2).isAfterOrEqualTo(dt1);
    }

    @Test
    void fixedClock() throws InterruptedException {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime dt1 = LocalDateTime.now(clock);
//        sleep(1);
        LocalDateTime dt2 = LocalDateTime.now(clock);
        LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(1);
        assertThat(dt1).isEqualTo(dt2);
        assertThat(dt3).isEqualTo(dt2.plusHours(1));
    }
}
