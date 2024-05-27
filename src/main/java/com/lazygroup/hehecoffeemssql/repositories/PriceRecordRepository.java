package com.lazygroup.hehecoffeemssql.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lazygroup.hehecoffeemssql.models.PriceRecord;

@Repository
public interface PriceRecordRepository extends JpaRepository<PriceRecord, Long> {
	Optional<PriceRecord> findByProductId(Long productId);
}
