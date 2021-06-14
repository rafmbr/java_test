package com.rafmbr.StockQuoteManager.entities;

import lombok.Data;

import javax.persistence.Id;

@Data
public class StockDescription {
    @Id
    private String stock;
    private String description;
}
