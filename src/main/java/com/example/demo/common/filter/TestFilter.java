package com.example.demo.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TestFilter extends OncePerRequestFilter{

	// ログインスタンスの取得
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
	@Override
    protected void initFilterBean() throws ServletException {
		logger.info("filter：application start");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

    	try {
    		logger.info(request.getRequestURI() + "_before");
    		filterChain.doFilter(request, response);
    		logger.info(request.getRequestURI() + "_after");
    		
        } catch(Exception e) {
    		logger.error(request.getRequestURI() + "_error");
        	throw e;
        }

    }

    @Override
    public void destroy() {
		logger.info("filter：application stop");
    }

}

// initFilterBean()  ：アプリの起動時に実行されるフィルター
// doFilterInternal()：requestの度に実行されるフィルター
// destroy()         ：アプリの終了時に実行されるフィルター
