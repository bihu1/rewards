package com.bihuniak.rewards.domain.reward;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OnePointStrategyTest
{
	private final OnePointStrategy onePointStrategy = new OnePointStrategy();

	@Test
	void shouldNotBeApplicableWhenAmountIsEqual50()
	{
		assertThat(onePointStrategy.isApplicable(50)).isFalse();
	}

	@Test
	void shouldNotBeApplicableWhenAmountIsLessThen50()
	{
		assertThat(onePointStrategy.isApplicable(49)).isFalse();
	}

	@Test
	void shouldBeApplicableWhenAmountIsGreaterThen50()
	{
		assertThat(onePointStrategy.isApplicable(51)).isTrue();
	}

	@Test
	void shouldBeApplicableWhenAmountIsLessOrEqual100()
	{
		assertThat(onePointStrategy.isApplicable(100)).isTrue();
	}

	@Test
	void shouldNotBeApplicableWhenAmountIsGreaterThen100()
	{
		assertThat(onePointStrategy.isApplicable(101)).isFalse();
	}

	@Test
	void shouldReturn50Points()
	{
		assertThat(onePointStrategy.getPoints(100)).isEqualTo(50);
	}
}