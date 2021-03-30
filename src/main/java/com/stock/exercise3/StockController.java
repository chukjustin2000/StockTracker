package com.stock.exercise3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;


    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public Iterable<Stock> list() {
        return stockService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> get(@PathVariable("id") int id) {
        return stockService.find(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Stock> create(@RequestBody Stock stock,
                       UriComponentsBuilder uriBuilder) {
        Stock created = stockService.create(stock);
        URI newBookUri = uriBuilder.path("/stocks/{id}").build(created.getId());
        return ResponseEntity.created(newBookUri).body(created);
    }
}
