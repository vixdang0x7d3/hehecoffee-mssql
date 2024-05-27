package com.lazygroup.hehecoffeemssql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lazygroup.hehecoffeemssql.models.StockRecord;

@Repository
public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {
}
