package com.example.demo.web.dto.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StockForm {
	// firld
	private String stockId; 
	private String stockTicker;
	private double ownPrice;
	private double ownCount;
}
