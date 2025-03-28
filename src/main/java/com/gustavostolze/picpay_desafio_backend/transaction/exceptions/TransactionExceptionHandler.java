package com.gustavostolze.picpay_desafio_backend.transaction.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gustavostolze.picpay_desafio_backend.transaction.InvalidTransactionException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class TransactionExceptionHandler {

	@ExceptionHandler(InvalidTransactionException.class)
	public ResponseEntity<StandardError> invalidTransaction(InvalidTransactionException e, HttpServletRequest request) {
		String error = "Invalid transaction";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
