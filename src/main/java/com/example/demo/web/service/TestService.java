package com.example.demo.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.db.entity.StockInfo;
import com.example.demo.db.mapper.StockInfoMapper;
import com.example.demo.web.dto.StockInDTO;
import com.example.demo.web.dto.StockOutDTO;
import com.example.demo.web.dto.rest.TestRequestJsonDTO;
import com.example.demo.web.dto.rest.TestResponseJsonDTO;

@Service
public class TestService {
	
	// ログインスタンスの取得
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StockInfoMapper stockInfoMapper;

	// URLからパラメータを取得するapi
	private static final String PARAM_GET_FROM_URL = "http://localhost:8081/api/urlParamGet/kenta?age=25";

	// RequestBody(String)からパラメータを取得するapi
	private static final String PARAM_GET_FROM_REQUESTBODY_STRING = "http://localhost:8081/api/requestBodyStringParamGet";

	// RequestBody(json)からパラメータを取得するapi
	private static final String PARAM_GET_FROM_REQUESTBODY_JSON = "http://localhost:8081/api/requestBodyJsonParamGet";

	public StockOutDTO stockGet(StockInDTO stockInDto) {
		
		StockOutDTO stockOutDTO = new StockOutDTO();
		
		// DB接続（SELECT）
		// https://www.shookuro.com/entry/2017/11/23/203318
		String stockId = stockInDto.getStockId();
		
		StockInfo stockInfo = new StockInfo();
		stockInfo = stockInfoMapper.search(stockId);

		stockOutDTO.setStockInfo(stockInfo);

		
		// API呼出(GET)
		// 別プロジェクトにAPIを作成する。
		// https://zenn.dev/sugaryo/books/spring-boot-run-up/viewer/api_controller
		RestTemplate restTemplate = new RestTemplate();
		
		// URLからパラメータを取得する方法
		paramGetFromUrlApi(restTemplate);
		// requestBody（String）,responseBody（String）
		paramGetFromRequestBodyStringApi(restTemplate);
		// requestBody（Json）,responseBody（Json）
		paramGetFromRequestBodyJsonApi(restTemplate);
				
		return stockOutDTO;
	}

	// URLからパラメータを取得する方法
	// https://b1san-blog.com/post/spring/spring-rest-template/
	private void paramGetFromRequestBodyJsonApi(RestTemplate restTemplate) {
		ResponseEntity<String> urlParamGetResponse = restTemplate.exchange(PARAM_GET_FROM_URL, HttpMethod.GET, null, String.class);
		logger.info(urlParamGetResponse.getBody());
	}

	// requestBody（String）,responseBody（String）
	// https://b1san-blog.com/post/spring/spring-rest-template/
	private void paramGetFromRequestBodyStringApi(RestTemplate restTemplate) {
		RequestEntity<String> request = RequestEntity.post(PARAM_GET_FROM_REQUESTBODY_STRING).body("body!!");
		ResponseEntity<String> bodyParamGetResponse = restTemplate.exchange(request, String.class);
		logger.info(bodyParamGetResponse.getBody());
		
	}

	// requestBody（Json）,responseBody（Json）
	// requestBody（DTO→Json）
	// responseBody（Json→DTO）
	private void paramGetFromUrlApi(RestTemplate restTemplate) {
		// Header情報
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// RequestBody情報（DTO）
		TestRequestJsonDTO testRequestJsonDTO = new TestRequestJsonDTO();
		testRequestJsonDTO.setRequestFirstName("田代");
		testRequestJsonDTO.setRequestLastName("健太");
		testRequestJsonDTO.setRequestIgnoreField("無視してください。");
		
		// RequestEntity情報の作成
		HttpEntity<TestRequestJsonDTO> requestEntity = new HttpEntity<>(testRequestJsonDTO, headers);

		// RESTAPI実行（DTO→Jsonに変換されて実行される）
		ResponseEntity<TestResponseJsonDTO> bodyJsonParamGetResponse = restTemplate.exchange(PARAM_GET_FROM_REQUESTBODY_JSON, HttpMethod.POST, requestEntity, TestResponseJsonDTO.class);
		logger.info(bodyJsonParamGetResponse.getBody().toString());
		
	}

}
