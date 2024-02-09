package com.bihuniak.rewards.domain.reward;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TwoPointStrategyTest
{
	private final TwoPointsStrategy twoPointsStrategy = new TwoPointsStrategy();

	@Test
	void shouldNotBeApplicableWhenAmountIsLessThen100()
	{
		assertThat(twoPointsStrategy.isApplicable(99)).isFalse();
	}

	@Test
	void shouldNotBeApplicableWhenAmountIsEqual100()
	{
		assertThat(twoPointsStrategy.isApplicable(100)).isFalse();
	}

	@Test
	void shouldBeApplicableWhenAmountGraterThen100()
	{
		assertThat(twoPointsStrategy.isApplicable(101)).isTrue();
	}

	@Test
	void shouldReturn52Points()
	{
		assertThat(twoPointsStrategy.getPoints(101)).isEqualTo(52);
	}

	@Test
	void shouldReturn150Points()
	{
		assertThat(twoPointsStrategy.getPoints(150)).isEqualTo(150);
	}
}