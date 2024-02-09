package com.bihuniak.rewards.domain.reward;

import com.bihuniak.rewards.domain.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RewardProcessorTest
{
	private RewardProcessor rewardProcessor;
	private RewardStrategy mockStrategy;

	@BeforeEach
	void init() {
		mockStrategy = Mockito.mock(RewardStrategy.class);
		rewardProcessor = new RewardProcessor(Set.of(mockStrategy));
	}

	@Test
	void shouldReturn0WhenNoneStrategyIsApplicable()
	{
		long amount = 100;
		Transaction transaction = new Transaction(0L, 0L, amount);
		when(mockStrategy.isApplicable(amount)).thenReturn(false);
		assertThat(rewardProcessor.calculatePoints(List.of(transaction))).isEqualTo(0);
	}

	@Test
	void shouldReturn50WhenStrategyIsApplicable()
	{
		long amount = 100;
		long reward = 100;
		Transaction transaction = new Transaction(0L, 0L, amount);
		when(mockStrategy.isApplicable(amount)).thenReturn(true);
		when(mockStrategy.getPoints(amount)).thenReturn(reward);
		assertThat(rewardProcessor.calculatePoints(List.of(transaction))).isEqualTo(reward);
	}

}