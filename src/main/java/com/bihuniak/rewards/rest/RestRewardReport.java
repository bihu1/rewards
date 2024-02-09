package com.bihuniak.rewards.rest;

import java.time.Month;
import java.util.Map;

record RestRewardReport(Map<Month, Long> rewards, Long total)
{
}
