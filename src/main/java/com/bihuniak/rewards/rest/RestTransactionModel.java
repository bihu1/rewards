package com.bihuniak.rewards.rest;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

record RestTransactionModel(@PositiveOrZero long userId, @Positive long amount)
{
}
