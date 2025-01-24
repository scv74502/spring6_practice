package com.example.tobyspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SortTest {
    Sort sort;

    // 테스트가 독립적으로 테스트되어야 하므로 객체를 매번 새로 생성한다.
    @BeforeEach
    void beforeEach(){
        // 준비
        sort = new Sort();
        System.out.println(this);
    }

    @Test
    void sort(){
        // 실행
        List<String> list = sort.sortByLength(Arrays.asList("AA", "B"));    // Arrays.asList()는 mutable하다

        // 결과 검증
        Assertions.assertThat(list).isEqualTo(List.of("B", "AA"));  // List.of()로 만들면 immutable하다
    }

    @Test
    void sortThreeItem(){
        // 실행
        List<String> list = sort.sortByLength(Arrays.asList("AA", "CCC", "B"));    // Arrays.asList()는 mutable하다

        // 결과 검증
        Assertions.assertThat(list).isEqualTo(List.of("B", "AA", "CCC"));  // List.of()로 만들면 immutable하다
    }

    @Test
    void sortAlreadySorted(){
        // 실행
        List<String> list = sort.sortByLength(Arrays.asList("B", "AA", "CCC"));    // Arrays.asList()는 mutable하다

        // 결과 검증
        Assertions.assertThat(list).isEqualTo(List.of("B", "AA", "CCC"));  // List.of()로 만들면 immutable하다
    }
}