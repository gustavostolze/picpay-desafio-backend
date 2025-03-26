package com.gustavostolze.picpay_desafio_backend.wallet;

public enum WalletType {

	COMMON(1),
	MERCHANT(2);
	
	private int value;
	
	private WalletType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}