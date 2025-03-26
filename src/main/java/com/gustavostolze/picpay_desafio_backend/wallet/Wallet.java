package com.gustavostolze.picpay_desafio_backend.wallet;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("WALLETS")
public class Wallet {

	@Id
	private Long id;
	private String fullName;
	private String document;
	private String email;
	private BigDecimal balance;
	private int type;
	
	public Wallet() {
	}
	
	public Wallet(Long id, String fullName, String document, String email, BigDecimal balance, int type) {
		this.id = id;
		this.fullName = fullName;
		this.document = document;
		this.email = email;
		this.balance = balance;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Wallet debit(BigDecimal value) {
		return new Wallet(null, fullName, document, email, balance.subtract(value), type);
	}
	
	public Wallet credit(BigDecimal value) {
		return new Wallet(null, fullName, document, email, balance.add(value), type);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		return Objects.equals(id, other.id);
	}
	
}
