package com.stock.exercise3;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class Stock {

    private int id;
    private String name;
    private double currentPrice;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;


    public Stock() {}

    public Stock(int id, String name, double currentPrice, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return String.format("Stock [id=%s, name=%s, current price=%s]",
                this.id, this.name, this.currentPrice);
    }
}
