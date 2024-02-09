package com.bihuniak.rewards.db;

import com.bihuniak.rewards.domain.transaction.Transaction;
import com.bihuniak.rewards.domain.transaction.TransactionNotFound;
import com.bihuniak.rewards.domain.transaction.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
class DbTransactionRepository implements TransactionRepository
{
	private final Logger logger = LoggerFactory.getLogger(DbTransactionRepository.class);

	private final JpaTransactionRepository repository;

	DbTransactionRepository(JpaTransactionRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public Transaction createTransaction(Transaction transaction)
	{
		TransactionEntity saved = repository.save(new TransactionEntity(0, transaction.userId(), transaction.amount()));
		logger.info("Add new transaction {}", saved);
		return new Transaction(saved.id, saved.userId, saved.amount);
	}

	@Override
	public void updateTransaction(Transaction transaction) throws TransactionNotFound
	{
		if(!repository.existsById(transaction.id()))
			throw new TransactionNotFound();
		TransactionEntity saved = repository.save(new TransactionEntity(transaction.id(), transaction.userId(),
			transaction.amount()));
		logger.info("Update transaction {}", saved);
	}
}
