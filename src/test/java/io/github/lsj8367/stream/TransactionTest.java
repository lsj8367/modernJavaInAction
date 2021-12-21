package io.github.lsj8367.stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import io.github.lsj8367.stream.Trader;
import io.github.lsj8367.stream.Transaction;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionTest {

    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        Trader raoul = new Trader("Raoul", "Seoul");
        Trader mario = new Trader("Mario", "Bucheon");
        Trader tom = new Trader("Tom", "Seoul");
        Trader henry = new Trader("Henry", "Seoul");

        transactions = Arrays.asList(
            new Transaction(henry, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(tom, 2012, 950)
        );
    }

    @Test
    void 문제_1() {
        // 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순 정렬
        final List<Transaction> results = transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .sorted(comparing(Transaction::getValue))
            .collect(toList());
        assertThat(results.get(0).getValue()).isEqualTo(300);
        assertThat(results.get(1).getValue()).isEqualTo(400);
    }

    @Test
    void 문제_2() {
        //거래자가 근무하는 모든 도시를 중복 없이 나열
        final List<String> results = transactions.stream()
            .map(transaction -> transaction.getTrader().getCity())
            .distinct()
            .collect(toList());
        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0)).isEqualTo("Seoul");
        assertThat(results.get(1)).isEqualTo("Bucheon");
    }

    @Test
    void 문제_3() {
        //서울에서 근무하는 모든 거래자를 찾아 이름순 정렬
        final List<Trader> results = transactions.stream()
            .map(Transaction::getTrader)
            .filter(trader -> trader.getCity().equals("Seoul"))
            .distinct()
            .sorted(comparing(Trader::getName))
            .collect(toList());

        assertThat(results.get(0).getName()).isEqualTo("Henry");
        assertThat(results.get(1).getName()).isEqualTo("Raoul");
        assertThat(results.get(2).getName()).isEqualTo("Tom");
    }

    @Test
    void 문제_4() {
        //모든 거래자의 이름을 알파벳순으로 정렬
        final List<Trader> results = transactions.stream()
            .map(Transaction::getTrader)
            .distinct()
            .sorted(comparing(Trader::getName))
            .collect(toList());

        assertThat(results.get(0).getName()).isEqualTo("Henry");
        assertThat(results.get(1).getName()).isEqualTo("Mario");
        assertThat(results.get(2).getName()).isEqualTo("Raoul");
        assertThat(results.get(3).getName()).isEqualTo("Tom");
    }

    @Test
    void 문제_5() {
        //밀라노에 거래자가 있는지
        final boolean isExist = transactions.stream()
            .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milano"));
        assertThat(isExist).isFalse();
    }

    @Test
    void 문제_6() {
        //서울에 거주하는 거래자의 모든 트랜잭션 값
        final List<Integer> results = transactions.stream()
            .filter(transaction -> transaction.getTrader().getCity().equals("Seoul"))
            .map(Transaction::getValue)
            .collect(toList());

        assertThat(results).containsExactly(300, 1000, 400, 950);
    }

    @Test
    void 문제_7() {
        //전체 트랜잭션 중 최댓값
        final Optional<Integer> expected = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max);

        final Integer result = expected.orElseThrow();
        assertThat(result).isEqualTo(1000);
    }

    @Test
    void 문제_8() {
        //전체 트랜잭션 중 최솟값
        final Optional<Integer> expected = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::min);

        final Integer result = expected.orElseThrow();
        assertThat(result).isEqualTo(300);
    }

}