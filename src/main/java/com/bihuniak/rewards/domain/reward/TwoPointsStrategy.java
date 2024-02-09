package com.bihuniak.rewards.domain.reward;

public class TwoPointsStrategy implements RewardStrategy
{
	@Override
	public boolean isApplicable(long amount)
	{
		return amount > 100;
	}

	@Override
	public long getPoints(long amount)
	{
		return 50 + (amount - 100) * 2;
	}
}
