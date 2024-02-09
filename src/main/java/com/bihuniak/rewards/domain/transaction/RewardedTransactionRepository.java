package com.bihuniak.rewards.domain.transaction;

import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.Map;

public interface RewardedTransactionRepository
{
	Map<Month, List<Transaction>> findAllUserTransactionsInPeriodStartsFromAmount(long userId, Period period,
	                                                                              long startAmount);
}
