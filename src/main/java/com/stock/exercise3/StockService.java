package com.stock.exercise3;

import java.util.Optional;

public interface StockService {

    Iterable<Stock> findAll();
    Stock create(Stock stock);
    Optional<Stock> find(int id);
}
