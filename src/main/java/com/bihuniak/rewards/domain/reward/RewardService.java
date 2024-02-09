package com.bihuniak.rewards.domain.reward;

public interface RewardService
{
	int REPORT_MONTHS = 3;
	int REWARDS_START_FROM_AMOUNT = 50;
	RewordReport calculateRewardReport(long userId);
}
