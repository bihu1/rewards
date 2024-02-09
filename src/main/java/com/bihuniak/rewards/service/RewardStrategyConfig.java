package com.bihuniak.rewards.service;

import com.bihuniak.rewards.domain.reward.OnePointStrategy;
import com.bihuniak.rewards.domain.reward.RewardStrategy;
import com.bihuniak.rewards.domain.reward.TwoPointsStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
class RewardStrategyConfig
{
	@Bean
	Set<RewardStrategy> getAvailableStrategies() {
		return Set.of(new OnePointStrategy(), new TwoPointsStrategy());
	}
}
