package com.gustavostolze.picpay_desafio_backend.notification;

public record Notification (String status, Data data){
	public boolean notificationSendedSuccessfully() {
		return !status.equals("fail");
	}
	public record Data(String message) {
	}
}
