package com.example.demo.web.dto.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

	@Setter
	@Getter
	@ToString
	public class ErrorForm {
		// firld
		private String errorType;
		private String errorMessage;
	}
