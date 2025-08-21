//package com.example.dealervehicle.exception;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		Map<String, Object> body = new HashMap<>();
//		body.put("status", HttpStatus.BAD_REQUEST.value());
//		Map<String, String> errors = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach(error -> {
//			String fieldName = ((FieldError) error).getField();
//			String errorMessage = error.getDefaultMessage();
//			errors.put(fieldName, errorMessage);
//		});
//		body.put("errors", errors);
//		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<Object> handleRuntime(RuntimeException ex) {
//		Map<String, Object> body = new HashMap<>();
//		body.put("status", HttpStatus.NOT_FOUND.value());
//		body.put("message", ex.getMessage());
//		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//	}
//}
//
//
//

