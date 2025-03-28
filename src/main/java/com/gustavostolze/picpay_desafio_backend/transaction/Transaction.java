package com.gustavostolze.picpay_desafio_backend.transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRANSACTIONS")
public class Transaction {

	@Id
	private Long id;
	private Long payer;
	private Long payee;
	private BigDecimal value;
	@CreatedDate
	private LocalDateTime createdAt;
	
	public Transaction() {
	}

	public Transaction(Long id, Long payer, Long payee, BigDecimal value, LocalDateTime createdAt) {
		this.id = id;
		this.payer = payer;
		this.payee = payee;
		this.value = value != null ? value.setScale(2, RoundingMode.UNNECESSARY) : BigDecimal.ZERO;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPayer() {
		return payer;
	}

	public void setPayer(Long payer) {
		this.payer = payer;
	}

	public Long getPayee() {
		return payee;
	}

	public void setPayee(Long payee) {
		this.payee = payee;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value != null ? value.setScale(2, RoundingMode.UNNECESSARY) : BigDecimal.ZERO;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
		Transaction other = (Transaction) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", payer=" + payer + ", payee=" + payee + ", value=" + value + ", createdAt="
				+ createdAt + "]";
	}
	
}
