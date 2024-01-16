package com.cathay.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cathay.dto.ExchangeRateDto;
import com.cathay.entity.ExchangeRateEntity;

public interface  ExchangeRateDao  extends JpaRepository<ExchangeRateEntity,Integer>{

	ExchangeRateEntity findByDate(Date date);
	List<ExchangeRateEntity> findByDateBetween(Date startDate, Date endDate);
}
