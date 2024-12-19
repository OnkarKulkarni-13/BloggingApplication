package com.bloggingapplication.payloads;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
//	public ApiResponse(String message, boolean success) {
//		this.message = message;
//		this.success = success;
//	}

	private String message;
	private Date date;
	private Boolean success;

}
