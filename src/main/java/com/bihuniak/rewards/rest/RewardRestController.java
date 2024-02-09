package com.bihuniak.rewards.rest;

import com.bihuniak.rewards.domain.reward.RewardService;
import com.bihuniak.rewards.domain.reward.RewordReport;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class RewardRestController
{
	private final RewardService rewardService;

	RewardRestController(RewardService rewardService)
	{
		this.rewardService = rewardService;
	}

	@GetMapping("/users/{id}/rewards/report")
	RestRewardReport getUserReward(@PathVariable @PositiveOrZero Long id) {
		RewordReport rewordReport = rewardService.calculateRewardReport(id);
		return new RestRewardReport(rewordReport.rewardsByMonth(),
			rewordReport.rewardsByMonth().values().stream().mapToLong(reword -> reword).sum());
	}
}
