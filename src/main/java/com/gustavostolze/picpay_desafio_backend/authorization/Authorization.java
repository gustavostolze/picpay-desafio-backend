package com.gustavostolze.picpay_desafio_backend.authorization;

public record Authorization (String status, Data data){
	public boolean isAuthorize() {
		return status.equals("success") && data.authorization;
	}
	public record Data(boolean authorization) {
	}
}
