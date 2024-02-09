package com.bihuniak.rewards.db;

import com.bihuniak.rewards.domain.transaction.RewardedTransactionRepository;
import com.bihuniak.rewards.domain.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Repository
class DbRewardedTransactionRepository implements RewardedTransactionRepository
{
	private final JpaTransactionRepository transactionRepository;

	DbRewardedTransactionRepository(JpaTransactionRepository transactionRepository)
	{
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Map<Month, List<Transaction>> findAllUserTransactionsInPeriodStartsFromAmount(long userId,
	                                                                                     Period period,
                                                                                         long startAmount
	)
	{
		ZonedDateTime now = ZonedDateTime.now();
		return transactionRepository.findByUserIdAndAmountGreaterThanAndTimestampBetween(userId, startAmount,
				now.minus(period).toInstant(),	now.toInstant())
			.stream()
			.collect(groupingBy(
				entity -> entity.timestamp.atZone(ZoneId.systemDefault()).getMonth(),
				mapping(entity -> new Transaction(entity.id, entity.userId, entity.amount), toList())
				)
			);
	}
}
