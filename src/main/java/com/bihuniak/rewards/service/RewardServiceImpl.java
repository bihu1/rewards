package com.bihuniak.rewards.service;

import com.bihuniak.rewards.domain.reward.RewardProcessor;
import com.bihuniak.rewards.domain.reward.RewardService;
import com.bihuniak.rewards.domain.reward.RewardStrategy;
import com.bihuniak.rewards.domain.reward.RewordReport;
import com.bihuniak.rewards.domain.transaction.RewardedTransactionRepository;
import com.bihuniak.rewards.domain.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class RewardServiceImpl implements RewardService
{
	private final RewardProcessor rewardProcessor;
	private final RewardedTransactionRepository rewardedTransactionRepository;

	RewardServiceImpl(Set<RewardStrategy> rewardStrategies, RewardedTransactionRepository rewardedTransactionRepository)
	{
		this.rewardProcessor = new RewardProcessor(rewardStrategies);
		this.rewardedTransactionRepository = rewardedTransactionRepository;
	}

	@Override
	public RewordReport calculateRewardReport(long userId)
	{
		Period period = Period.ofMonths(REPORT_MONTHS);
		Map<Month, List<Transaction>> transactionsByMonth =
			rewardedTransactionRepository.findAllUserTransactionsInPeriodStartsFromAmount(userId, period, REWARDS_START_FROM_AMOUNT);
		Map<Month, Long> rewordsByMonth = transactionsByMonth.entrySet().stream()
			.collect(Collectors.toMap(Map.Entry::getKey, entry -> rewardProcessor.calculatePoints(entry.getValue())));
		return new RewordReport(rewordsByMonth);
	}
}
