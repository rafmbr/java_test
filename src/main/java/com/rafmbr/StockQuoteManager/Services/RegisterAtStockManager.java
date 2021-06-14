package com.rafmbr.StockQuoteManager.Services;

import com.rafmbr.StockQuoteManager.entities.Notification;
import com.rafmbr.StockQuoteManager.feign.StockQuoteManagerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterAtStockManager {

    private StockQuoteManagerFeign stockQuoteManagerFeign;


    @Autowired
    private RegisterAtStockManager(StockQuoteManagerFeign stockQuoteManagerFeign) {
        this.stockQuoteManagerFeign = stockQuoteManagerFeign;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void register() {
        Notification notification = new Notification();
        notification.setHost("127.0.0.1");
        notification.setPort(8081);
        List<Notification> response = stockQuoteManagerFeign.NotificateStockManager(notification);
        System.out.println("Registered and at stock manager: " + response.get(0).getHost() + ":" + response.get(0).getPort());
    }
}
