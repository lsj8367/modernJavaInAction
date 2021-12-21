package io.github.lsj8367.optional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class OptionalTest {

    @Test
    void test() {
        final Car car = null;
        final Optional<Car> optCar = Optional.ofNullable(car);
        assertThat(optCar).isEqualTo(Optional.empty());
    }

    @Test
    void getCarInsuranceName() {
        Optional<Person> person = Optional.of(new Person(Optional.empty()));

        final String unknown = person.flatMap(Person::getCar)
            .flatMap(Car::getInsurance)
            .map(Insurance::getName)
            .orElse("Unknown");

        assertThat(unknown).isEqualTo("Unknown");
    }
}
