package edu.hw3.task6;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public record Stock(String ticker, double price) implements Comparable<Stock> {
    public Stock {
        Objects.requireNonNull(ticker);
        if (price <= 0) {
            throw new IllegalArgumentException("Stock price must be positive");
        }
    }

    public int compareTo(@NotNull Stock otherStock) {
        return Double.compare(price, otherStock.price);
    }
}
