package edu.hw3.task6;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StockMarket implements StockMarketInterface {
    private final static Logger LOGGER = LogManager.getLogger();

    private final PriorityQueue<Stock> priorityQueue;

    public StockMarket() {
        priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
    }

    @Override
    public void add(Stock stock) {
        if (priorityQueue.contains(stock)) {
            throw new IllegalArgumentException("The stock is already on the market");
        }
        LOGGER.trace("Adding stock {} to the market", stock);
        priorityQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        Objects.requireNonNull(stock);
        LOGGER.trace("Removing stock {} from the market", stock);
        if (!priorityQueue.remove(stock)) {
            throw new IllegalArgumentException("Stock is not present on the market");
        }
    }

    @Override
    public Stock mostValuableStock() {
        Stock peekedStock = priorityQueue.peek();
        LOGGER.trace("Peeking stock {} from the market", peekedStock);
        return peekedStock;
    }
}
