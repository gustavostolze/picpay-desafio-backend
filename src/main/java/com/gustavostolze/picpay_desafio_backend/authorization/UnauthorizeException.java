package com.gustavostolze.picpay_desafio_backend.authorization;

public class UnauthorizeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizeException(String msg) {
		super(msg);
	}
}
