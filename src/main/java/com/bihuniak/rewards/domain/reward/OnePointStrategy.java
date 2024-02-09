package com.bihuniak.rewards.domain.reward;

public class OnePointStrategy implements RewardStrategy
{
	@Override
	public boolean isApplicable(long amount)
	{
		return amount > 50 && amount <= 100;
	}

	@Override
	public long getPoints(long amount)
	{
		return amount - 50;
	}
}
