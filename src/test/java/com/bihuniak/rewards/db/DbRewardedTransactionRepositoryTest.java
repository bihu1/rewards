package com.bihuniak.rewards.db;

import com.bihuniak.rewards.domain.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbRewardedTransactionRepositoryTest
{
	@Autowired
	private DbRewardedTransactionRepository dbRewardedTransactionRepository;
	@Autowired
	private JpaTransactionRepository repository;

	@Test
	void shouldFindAllUserTransactionsInPeriodStartsFromAmount()
	{
		long expectedAmountSum = 600;
		Period period = Period.ofMonths(3);
		ZonedDateTime now = ZonedDateTime.now();
		TransactionEntity thisMonth = new TransactionEntity(0, 0, 150, now.toInstant());
		TransactionEntity lastMonth = new TransactionEntity(0, 0, 150, now.minusMonths(1).toInstant());
		TransactionEntity twoMonthsAgo = new TransactionEntity(0, 0, 150, now.minusMonths(2).toInstant());
		TransactionEntity threeMonthsAgo = new TransactionEntity(0, 0, 150,
			now.minusMonths(3).plusHours(1).toInstant());

		TransactionEntity tooOld = new TransactionEntity(0, 0, 100, now.minusMonths(3).toInstant());
		TransactionEntity withoutReword = new TransactionEntity(0, 0, 40, now.minusMonths(1).toInstant());
		TransactionEntity differentUser = new TransactionEntity(0, 1, 150, now.minusMonths(1).toInstant());
		repository.saveAll(List.of(thisMonth, lastMonth, twoMonthsAgo, threeMonthsAgo, tooOld, withoutReword, differentUser));

		Map<Month, List<Transaction>> allUserTransactionsInPeriodStartsFromAmount = dbRewardedTransactionRepository
			.findAllUserTransactionsInPeriodStartsFromAmount(0, period, 50);
		long sum = allUserTransactionsInPeriodStartsFromAmount.values().stream()
			.flatMapToLong(transactions -> transactions.stream().mapToLong(Transaction::amount))
			.sum();
		assertThat(sum).isEqualTo(expectedAmountSum);
	}
}