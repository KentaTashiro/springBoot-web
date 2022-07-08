package com.example.demo.web.dto;

import com.example.demo.db.entity.StockInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StockOutDTO {
	// firld
	private StockInfo stockInfo;
}
