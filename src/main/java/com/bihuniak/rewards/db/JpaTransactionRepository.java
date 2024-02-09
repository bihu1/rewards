package com.bihuniak.rewards.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Set;

interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Long>
{
	Set<TransactionEntity> findByUserIdAndAmountGreaterThanAndTimestampBetween(long userId, long amount,
	                                                                           Instant to, Instant from);
}
