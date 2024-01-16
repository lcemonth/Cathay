package com.cathay.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.error.AssertionErrorMessagesAggregrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.cathay.dao.ExchangeRateDao;
import com.cathay.dto.ExchangeRateDto;
import com.cathay.entity.ExchangeRateEntity;

@SpringBootTest
@Transactional
class ExchangeRateServiceImplTest {

	@Autowired
	private ExchangeRateDao exchangeRateDao;
	
	@Test
	public void findByDateBetweenUsdToNtd() throws ParseException {
		Date startDate = Date.valueOf("2023-12-01");
		Date endDate = Date.valueOf("2024-01-15");
		
		List<ExchangeRateEntity> exchangeRateList = exchangeRateDao.findByDateBetween(startDate, endDate);
		
		assertNotNull(exchangeRateList);
		assertEquals("2023-12-01", exchangeRateList.get(0).getDate().toString());
		assertEquals("31.475", exchangeRateList.get(0).getUsdToNtd().toString());
		

		List<ExchangeRateEntity> exchangeRateList2 = exchangeRateDao.findByDateBetween(Date.valueOf("2024-12-01"), Date.valueOf("2024-12-03"));
		assertEquals(0,exchangeRateList2.size());
		
	}
	
	@Test
	public void addExchangeRate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = dateFormat.parse("2024-02-01");
		
		ExchangeRateEntity exchangeRateEntity = new ExchangeRateEntity();
		exchangeRateEntity.setDate(date);
		exchangeRateEntity.setUsdToNtd(22.22d);

		List<ExchangeRateEntity> exchangeRateList = new ArrayList<ExchangeRateEntity>();
		exchangeRateList.add(exchangeRateEntity);

		
		exchangeRateDao.saveAll(exchangeRateList);
		
		ExchangeRateEntity findExchangeRateEntity =exchangeRateDao.findByDate(exchangeRateEntity.getDate());
		
		assertNotNull(findExchangeRateEntity);
		assertEquals("2024-02-01",findExchangeRateEntity.getDate().toString());
		assertEquals("22.22",findExchangeRateEntity.getUsdToNtd().toString());
		
	}
}
