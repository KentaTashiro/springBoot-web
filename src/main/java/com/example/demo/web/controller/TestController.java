package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.common.exception.TestException1;
import com.example.demo.common.exception.TestException2;
import com.example.demo.web.dto.StockInDTO;
import com.example.demo.web.dto.StockOutDTO;
import com.example.demo.web.dto.form.IndexForm;
import com.example.demo.web.dto.form.StockForm;
import com.example.demo.web.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	TestService stockService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		IndexForm indexForm = new IndexForm();
		indexForm.setId("");

		model.addAttribute("indexForm", indexForm);
		return "index";
	}

	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public String getStock(@ModelAttribute IndexForm indexForm, Model model) throws TestException1, TestException2 {
		
		// 例外ハンドリング
		switch (indexForm.getId()) {
		case "1":
			throw new TestException1();

		case "2":
			throw new TestException2();

		default:
			break;
		}
		
		// service呼出（DBのSELECT、APIの呼出）
		StockInDTO stockInDto = new StockInDTO();
		StockOutDTO stockOutDto = new StockOutDTO();
		stockInDto.setStockId(indexForm.getId());
		stockOutDto = stockService.stockGet(stockInDto);
		
		// 遷移後の画面表示内容を編集
		StockForm stockForm = new StockForm();
		stockForm.setStockId(stockOutDto.getStockInfo().getStockId());
		stockForm.setStockTicker(stockOutDto.getStockInfo().getStockTicker());
		stockForm.setOwnPrice(stockOutDto.getStockInfo().getOwnPrice());
		stockForm.setOwnCount(stockOutDto.getStockInfo().getOwnCount());

		model.addAttribute("stockForm", stockForm);
		return "stock";
	}

}