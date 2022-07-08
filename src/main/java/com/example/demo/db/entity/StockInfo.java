package com.example.demo.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockInfo {

    private String stockId;

    private String stockTicker;

    private Double ownPrice;

    private Double ownCount;

}