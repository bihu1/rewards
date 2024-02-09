package com.bihuniak.rewards.domain.reward;

import java.time.Month;
import java.util.Map;

public record RewordReport(Map<Month, Long> rewardsByMonth)
{
}
