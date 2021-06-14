package com.rafmbr.StockQuoteManager.repository;

import com.rafmbr.StockQuoteManager.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findByQuote(String id);
}
