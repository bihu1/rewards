package com.bihuniak.rewards.db;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Check;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "transaction")
@Access(AccessType.FIELD)
class TransactionEntity
{
	@Id
	@GeneratedValue
	public final Long id;
	public final Long userId;
	@Check(constraints = "amount > 0")
	public final Long amount;
	public final Instant timestamp;

	private TransactionEntity()
	{
		this.id = null;
		this.userId = null;
		this.amount = null;
		this.timestamp = null;
	}

	TransactionEntity(long id, long userId, long amount, Instant timestamp)
	{
		this.id = id;
		this.userId = userId;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	TransactionEntity(long id, long userId, long amount)
	{
		this(id, userId, amount, Instant.now());
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TransactionEntity that = (TransactionEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(amount, that.amount);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, userId, amount);
	}

	@Override
	public String toString()
	{
		return "TransactionEntity{" +
			"id=" + id +
			", userId=" + userId +
			", amount=" + amount +
			'}';
	}
}
