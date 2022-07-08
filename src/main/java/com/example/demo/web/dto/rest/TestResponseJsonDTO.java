package com.example.demo.web.dto.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TestResponseJsonDTO {

	// 氏名（姓）
	@JsonProperty("responseFirstName")
	private String responseFirstName;

	// 氏名（名）
	@JsonProperty("responseLastName")
	private String responseLastName;
}
