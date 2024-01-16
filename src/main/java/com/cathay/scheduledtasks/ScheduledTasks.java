package com.cathay.scheduledtasks;

import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cathay.dto.ExchangeRateDto;
import com.cathay.service.ExchangeRateService;
import com.cathay.service.impl.ExchangeRateServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class ScheduledTasks {

	static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@Scheduled(cron = "0 0 18 * * ?" ,zone = "Asia/Taipei" ) // 每日 18:00 觸發
    public void fetchAndSaveForexData() {
		
		LOG.info("開始執行18:00 fetchAndSaveForexData排程");
		
        //  API 取得資料
        String apiUrl = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
        String jsonResponse = new RestTemplate().getForObject(apiUrl, String.class);

        try {

        	// 解析 JSON 資料 轉換List
        	Type exchangeRateListType = new TypeToken<List<ExchangeRateDto>>(){}.getType();
            
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            List<ExchangeRateDto> exchangeRateList = new Gson().fromJson(jsonResponse, exchangeRateListType);
            
            Integer count = exchangeRateService.addExchangeRate(exchangeRateList);
            
        LOG.info("執行結束 新增筆數: " + count);
    		
            
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("fetchAndSaveForexData排程 執行失敗，Exception: "+e.getMessage());
        }
    }
}
