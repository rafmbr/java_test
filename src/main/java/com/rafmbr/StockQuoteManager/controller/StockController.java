package com.rafmbr.StockQuoteManager.controller;

import com.rafmbr.StockQuoteManager.entities.Quote;
import com.rafmbr.StockQuoteManager.feign.StockQuoteManagerFeign;
import com.rafmbr.StockQuoteManager.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class StockController {
    private QuoteRepository quoteRepository;
    private StockQuoteManagerFeign stockQuoteManagerFeign;

    @Autowired
    public StockController(QuoteRepository quoteRepository,
                           StockQuoteManagerFeign stockQuoteManagerFeign) {
        this.quoteRepository = quoteRepository;
        this.stockQuoteManagerFeign = stockQuoteManagerFeign;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quote save(@RequestBody Quote quote) {
        if (stockQuoteManagerFeign.getStockDescriptionById(quote.getQuote()) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Stock not found at stock manager"
            );
        }
        return quoteRepository.save(quote);
    }

    @GetMapping
    public List<Quote> findAllQuotes(){
        return quoteRepository
                .findAll();
    }

    @GetMapping("{id}")
    public List<Quote> findQuoteById(@PathVariable String id){
        return quoteRepository
                .findByQuote(id);
    }

}
