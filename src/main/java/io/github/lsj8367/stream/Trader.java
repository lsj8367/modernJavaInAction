package io.github.lsj8367.stream;

public class Trader {
    private final String name;
    private final String city;

    public Trader(final String name, final String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader: " + name + " in " + city;
    }

}
