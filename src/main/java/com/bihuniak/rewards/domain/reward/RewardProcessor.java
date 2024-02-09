package com.bihuniak.rewards.domain.reward;

import com.bihuniak.rewards.domain.transaction.Transaction;

import java.util.List;
import java.util.Set;

public class RewardProcessor
{
	private final Set<RewardStrategy> strategies;

	public RewardProcessor(Set<RewardStrategy> strategies)
	{
		this.strategies = strategies;
	}

	public long calculatePoints(List<Transaction> transactions) {
		return transactions.stream()
			.mapToLong(Transaction::amount)
			.map(amount -> strategies.stream()
				.filter(strategy -> strategy.isApplicable(amount))
				.findAny()
				.map(strategy -> strategy.getPoints(amount))
				.orElse(0L)
			).sum();
	}
}
