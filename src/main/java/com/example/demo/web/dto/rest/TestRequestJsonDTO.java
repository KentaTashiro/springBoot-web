package com.example.demo.web.dto.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestRequestJsonDTO {
	
	// 氏名（姓）
	private String requestFirstName;

	// 氏名（名）
	private String requestLastName;
	
	// 無視項目
	@JsonIgnore
	private String requestIgnoreField;
	
}

// requestBody(json)からパラメータを取得する方法
// https://b1san-blog.com/post/spring/spring-jackson/

// @JsonProperty：フィールドに設定したいリクエストの項目名を指定する。
// @JsonIgnore