package com.bloggingapplication.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bloggingapplication.payloads.ApiResponse;

//import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(), new Date(), false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<ApiResponse> invalidUserIdException(InvalidUserIdException ex) {
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(), new Date(), false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(UserNameNotFoundException.class)
	public ResponseEntity<ApiResponse> userNameNotFoundException(UserNameNotFoundException ex) {
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(), new Date(), false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

		Map<String, String> mapError = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			mapError.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(mapError, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponse> entityNotFoundException(EntityNotFoundException ex) {
		// Map<String, String> map = new HashMap<>();
		// map.put("error", ex.getMessage()); // Assuming you want to include the
		// exception message
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(), new Date(), false);
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(TitleSearchNotPresentException.class)
	public ResponseEntity<ApiResponse> titleSearchNotPresentException(TitleSearchNotPresentException ex) {
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(), new Date(), false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
