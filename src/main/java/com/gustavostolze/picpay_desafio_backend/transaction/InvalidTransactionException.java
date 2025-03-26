package com.gustavostolze.picpay_desafio_backend.transaction;

public class InvalidTransactionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidTransactionException(String msg) {
		super(msg);
	}
}
