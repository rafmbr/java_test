package com.rafmbr.StockQuoteManager.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockcache")
public class CacheController {

    @DeleteMapping
    @CacheEvict(value="idStocksCache", allEntries = true)
    public void deleteCache(){
        System.out.println("Cache deletado!!!!!!!");
    }

}
