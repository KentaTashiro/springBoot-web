package com.example.demo.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAOP {
	
	// ログインスタンスの取得
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.example.demo.web.controller.TestController.*(..))")
	public Object aspectHelloController(ProceedingJoinPoint pjp) throws Throwable {
		try {
			logger.info("TestController_before");
			Object result = pjp.proceed();
			logger.info("TestController_after");
			return result;
		} catch (Throwable e) {
			logger.error("TestController_error");
			throw e;
		}
	}

//	@Before("execution(* com.example.demo.web.controller.HelloController.*(..))")
//	public void aspectBeforeHelloController() {
//			System.out.println("aop：HelloController前処理");
//	}
//
//	@After("execution(* com.example.demo.web.controller.HelloController.*(..))")
//	public void aspectAfterHelloController() {
//			System.out.println("aop：HelloController後処理");
//	}

}

// @Aspect：aopの処理を書く時のお決まり

// https://qiita.com/NagaokaKenichi/items/386af61b6866d60964e8
// @Before        ：対象のメソッド呼び出し前に実行される。
// @After         ：対象のメソッド呼び出し後に実行される。
// @AfterReturning：対象のメソッドが正常終了した後に実行される。
// @AfterThrowing ：対象のメソッドが異常終了（例外発生）した後に実行される。
// @Around        ：対象のメソッドの代わりに実行される。（処理の前後に好きな処理を記述可能）

// http://teqspaces.com/Spring/9
// ProceedingJoinPoint：実行するメソッドの情報が取得できるほか、メソッド自体の実行も行える。
// proceed()          ：実際のメソッドが呼ばれ、戻り値には実際のメソッドの戻り値が格納されている。
