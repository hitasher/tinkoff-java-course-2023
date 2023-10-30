package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask6 {

    private final static Stock appleStock = new Stock("AAPL", 3);
    private final static Stock teslaStock = new Stock("TSLA", 4);
    private final static Stock amazonStock = new Stock("AMZN", 5);

    @ParameterizedTest
    @NullSource
    void testStockConstructor_ShouldThrowNullPointerException(String ticker) {
        assertThatThrownBy(
            () -> new Stock(ticker, 1)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -0.01, -100})
    void testStockConstructor_ShouldThrowIllegalArgumentException(double price) {
        assertThatThrownBy(
            () -> new Stock("AAPL", price)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void stockMarket_ShouldReturnMostValuableStock() {
        StockMarket stockMarket = new StockMarket();
        assertThat(stockMarket.mostValuableStock()).isNull();
        stockMarket.add(appleStock);
        assertThat(stockMarket.mostValuableStock()).isEqualTo(appleStock);
        stockMarket.add(amazonStock);
        stockMarket.add(teslaStock);
        assertThat(stockMarket.mostValuableStock()).isEqualTo(amazonStock);
        stockMarket.remove(amazonStock);
        assertThat(stockMarket.mostValuableStock()).isEqualTo(teslaStock);
    }

    @Test
    void addToStockMarket_ShouldThrowIllegalArgumentException() {
        StockMarket stockMarket = new StockMarket();
        stockMarket.add(teslaStock);
        assertThatThrownBy(
            () -> stockMarket.add(teslaStock)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void removeFromStockMarket_ShouldThrowIllegalArgumentException() {
        StockMarket stockMarket = new StockMarket();
        assertThatThrownBy(
            () -> stockMarket.remove(amazonStock)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullSource
    void addToStockMarket_ShouldThrowNullPointerException(Stock stock) {
        StockMarket stockMarket = new StockMarket();
        assertThatThrownBy(
            () -> stockMarket.add(stock)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void removeFromStockMarket_ShouldThrowNullPointerException(Stock stock) {
        StockMarket stockMarket = new StockMarket();
        assertThatThrownBy(
            () -> stockMarket.remove(stock)
        ).isInstanceOf(NullPointerException.class);
    }
}
