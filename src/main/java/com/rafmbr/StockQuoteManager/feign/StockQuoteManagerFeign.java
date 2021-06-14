package com.rafmbr.StockQuoteManager.feign;

import com.rafmbr.StockQuoteManager.entities.Notification;
import com.rafmbr.StockQuoteManager.entities.StockDescription;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "StockManagerService", url = "http://localhost:8080/")
public interface StockQuoteManagerFeign {
    @GetMapping("/stock")
    @Cacheable(value = "allStocksCache")
    List<StockDescription> getStockDescription();

    @GetMapping("/stock/{id}")
    @Cacheable(value = "idStocksCache")
    StockDescription getStockDescriptionById(@PathVariable("id") String id);

    @PostMapping(value="/notification", consumes = "application/json", produces = "application/json")
    List<Notification> NotificateStockManager(@RequestBody Notification notification);

}
