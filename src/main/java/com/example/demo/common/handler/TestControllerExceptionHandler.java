package com.example.demo.common.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.common.exception.TestException1;
import com.example.demo.common.exception.TestException2;

@ControllerAdvice
public class TestControllerExceptionHandler {

	// ログインスタンスの取得
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler({TestException1.class})
	public String testException1Handle() {
		logger.error("TestException1");
		return "error";
		
	}

	@ExceptionHandler({TestException2.class})
	@ResponseStatus(HttpStatus.IM_USED)
	@ResponseBody
	public Map<String, Object> testException2Handle() {
		
		logger.error("TestException2");
		Map<String, Object> errorMap = new HashMap<String, Object>();
		errorMap.put("message", "入力値2の場合はHTTPresponseとしてエラーを返す。");
		errorMap.put("status", HttpStatus.IM_USED);
		return errorMap;
	
	}
}

// https://qiita.com/NagaokaKenichi/items/2f199134a881a776b717
// @ControllerAdvice：全てのControllerクラスで発生した例外に対する共通の処理を行うクラス
// @ResponseStatus  ：Response時のHTTPステータスコードを指定する。
// @ResponseBody    ：Response時のBODYを編集するメソッドであることを指定する。
