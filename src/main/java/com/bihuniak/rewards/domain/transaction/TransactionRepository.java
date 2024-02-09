package com.bihuniak.rewards.domain.transaction;

public interface TransactionRepository
{
	Transaction createTransaction(Transaction transaction);
	void updateTransaction(Transaction transaction) throws TransactionNotFound;
}
