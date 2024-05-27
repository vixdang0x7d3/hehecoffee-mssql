package com.lazygroup.hehecoffeemssql.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.dtos.PriceRecordDto;
import com.lazygroup.hehecoffeemssql.mappers.PriceRecordMapper;
import com.lazygroup.hehecoffeemssql.models.PriceRecord;
import com.lazygroup.hehecoffeemssql.repositories.PriceRecordRepository;
import com.lazygroup.hehecoffeemssql.services.PriceRecordService;

@Service
public class PriceRecordServiceImpl implements PriceRecordService {

	private PriceRecordRepository priceRecordRepo;

	@Autowired
	PriceRecordServiceImpl(PriceRecordRepository priceRecordRepo) {
		this.priceRecordRepo = priceRecordRepo;
	}

	@Override
	public PriceRecordDto findByProductId(Long productId) {
		PriceRecord record = priceRecordRepo.findByProductId(productId).orElseThrow();

		return PriceRecordMapper.mapToDto(record);
	}
}
