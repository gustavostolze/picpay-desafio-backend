package com.gustavostolze.picpay_desafio_backend.notification;

public class NotificationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotificationException(String msg) {
		super(msg);
	}
}
