package io.github.lsj8367.optional;

import java.util.Optional;

public class Person {

    private Optional<Car> car;

    public Person(final Optional<Car> car) {
        this.car = car;
    }

    public Optional<Car> getCar() {
        return car;
    }
}
