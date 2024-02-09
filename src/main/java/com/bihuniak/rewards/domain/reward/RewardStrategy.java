package com.bihuniak.rewards.domain.reward;

public interface RewardStrategy
{
	boolean isApplicable(long amount);
	long getPoints(long amount);
}
