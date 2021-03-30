package com.stock.exercise3;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryStockService implements StockService {

    private final Map<Integer, Stock> stocks = new ConcurrentHashMap<>();

    @Override
    public Iterable<Stock> findAll() {
        return stocks.values();
    }

    @Override
    public Stock create(Stock stock) {
        stocks.put(stock.getId(), stock);
        return stock;
    }

    @Override
    public Optional<Stock> find(int id) {
        return Optional.ofNullable(stocks.get(id));
    }
}
