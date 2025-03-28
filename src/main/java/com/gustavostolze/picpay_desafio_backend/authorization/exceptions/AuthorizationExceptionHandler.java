package com.gustavostolze.picpay_desafio_backend.authorization.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gustavostolze.picpay_desafio_backend.authorization.UnauthorizeException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AuthorizationExceptionHandler {

	@ExceptionHandler(UnauthorizeException.class)
	public ResponseEntity<StandardError> unauthorize(UnauthorizeException e, HttpServletRequest request) {
		String message = "Unauthorize transaction";
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(LocalDateTime.now(), status.value(), message, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
