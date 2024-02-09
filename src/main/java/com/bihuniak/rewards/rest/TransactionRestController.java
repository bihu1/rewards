package com.bihuniak.rewards.rest;

import com.bihuniak.rewards.domain.transaction.Transaction;
import com.bihuniak.rewards.domain.transaction.TransactionNotFound;
import com.bihuniak.rewards.domain.transaction.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class TransactionRestController
{
	private final TransactionRepository transactionRepository;

	TransactionRestController(TransactionRepository transactionRepository)
	{
		this.transactionRepository = transactionRepository;
	}

	@PostMapping("/transactions")
	@ResponseStatus(HttpStatus.CREATED)
	RestTransactionId createTransaction(@RequestBody @Valid RestTransactionModel transaction) {
		Transaction createdTransaction =
			transactionRepository.createTransaction(new Transaction(null, transaction.userId(), transaction.amount()));
		return new RestTransactionId(createdTransaction.id());
	}

	@PutMapping("/transactions/{id}")
	ResponseEntity<Void> updateTransaction(@PathVariable Long id, @RequestBody @Valid RestTransactionModel transaction) {
		try
		{
			transactionRepository.updateTransaction(new Transaction(id, transaction.userId(), transaction.amount()));
		} catch (TransactionNotFound e)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
