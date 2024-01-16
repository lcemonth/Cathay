package com.cathay.scheduledtasks;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.cathay.dao.ExchangeRateDao;
import com.cathay.entity.ExchangeRateEntity;

@SpringBootTest
@Transactional
class ScheduledTasksTest {

	@Autowired
	private ScheduledTasks scheduledTasks;

	@Autowired
	private ExchangeRateDao exchangeRateDao;
	
	@Test
	public void fetchAndSaveForexData() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date date = new Date( dateFormat.parse("2024-01-15").getTime());
		
		//手動執行 ScheduledTasks
		scheduledTasks.fetchAndSaveForexData();
		
		//驗證 ScheduledTasks執行過後的資料
		ExchangeRateEntity exchangeRateEntity = exchangeRateDao.findByDate(date);
		
		assertEquals("2024-01-15", exchangeRateEntity.getDate().toString());
		assertEquals(31.215, exchangeRateEntity.getUsdToNtd());
		
	}

}
