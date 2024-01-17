國泰Java
===

## 需求項目
---
語言:
* Java 11

框架：
* Spring Boot 
* Spring MVC 
* Spring Data Jpa

專案類型：
* Maven

資料庫：
* H2

情境：
* 提供RestFul API，達到以下幾件事：
    1. 查詢匯率資料 :「日期」為查詢條件
      > 查詢時間格式範例：2024/01/01
---


## 資料庫設計
---
* 匯率資料表[EXCHANGE_RATE]

|欄位代號        |欄位名稱        |型別 |欄位大小   |必填      |
|--------------|---------------|----------|----------|---------|
|*EXCHANGE_ID   |ID             |INTEGER|          |●|
|EXCHANGE_Date |匯率日期)      |Date|        |●|
|USD_TO_NTD |美金匯換台幣匯率     |DOUBLE|         |●|


EXCHANGE_RATE.sql
---
```=sql
DROP TABLE IF EXISTS EXCHANGE_RATE;

-- 幣別對應表
CREATE TABLE EXCHANGE_RATE (
	EXCHANGE_ID INTEGER PRIMARY KEY auto_increment, -- ID
    	EXCHANGE_Date Date NOT NULL,
   	USD_TO_NTD DOUBLE NOT NULL	--美元兌換新台幣

);

-- 測試資料
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231201', '31.475');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231204', '31.415');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231205', '31.493');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231206', '31.505');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231207', '31.529');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231208', '31.374');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231211', '31.512');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231212', '31.509');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231213', '31.526');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231214', '31.33');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231215', '31.268');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231218', '31.314');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231219', '31.361');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231220', '31.31');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231221', '31.282');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231222', '31.203');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231225', '31.14');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231226', '31.065');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231227', '30.88');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231228', '30.718');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20231229', '30.735');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20240102', '30.866');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20240103', '31.01');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20240104', '31.016');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20240105', '31.025');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20240108', '31.001');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20240109', '31.023');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20240110', '31.14');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20240111', '31.103');
INSERT INTO EXCHANGE_RATE (EXCHANGE_DATE, USD_TO_NTD) VALUES ('20240112', '31.129');


```

---

## API設計
---
* ExchangeRate API[ExchangeRateController]

|Http狀態|URI                |說明
|-------|-------------------|----------|
|POST   |/getExchangeRates       |查詢指定日期美金兌換台幣匯率



---

## URL
---

* URL : http://127.0.0.1:8080/Cathay/
* swagger-ui : http://127.0.0.1:8080/Cathay/swagger-ui.html#/

---


## 定時排程
---

排程清單
| 排程名稱 | 執行時間 | 內容說明 |
| -------- | -------- | -------- |
| fetchAndSaveForexData     | 每日18:00     | 呼叫API，取得每日匯率資料，寫入DB Table     |







## UnitTest
---
1. API測試
2. 排程測試

### API 測試
```=java
package com.cathay.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ExchangeRateControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@DisplayName("測試API成功回傳")
	@Test
    public void getUsdToNtdExchangeRateData() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/ExchangeRate/v1/getExchangeRates")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ " \"startDate\": \"2024/01/11\",\r\n"
						+ " \"endDate\": \"2024/01/15\",\r\n"
						+ " \"currency\": \"usd\"\r\n"
						+ "}");
		
		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(jsonPath("$.currency[0].date",equalTo("20240111")))
			.andExpect(jsonPath("$.currency[0].usd",equalTo(31.103)))
			.andExpect(jsonPath("$.currency[1].date",equalTo("20240112")))
			.andExpect(jsonPath("$.currency[1].usd",equalTo(31.129)))
			.andExpect(jsonPath("$.error.code",equalTo("0000")))
			.andExpect(jsonPath("$.error.message",equalTo("成功")))
			.andExpect(status().is(200));
    }
	
	@DisplayName("測試API失敗回傳")
	@Test
	public void getUsdToNtdExchangeRateData2() throws Exception {
		RequestBuilder requestBuilderDateError = MockMvcRequestBuilders
				.post("/ExchangeRate/v1/getExchangeRates")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ " \"startDate\": \"2023/01/01\",\r\n"
						+ " \"endDate\": \"2024/01/16\",\r\n"
						+ " \"currency\": \"usd\"\r\n"
						+ "}");
		mockMvc.perform(requestBuilderDateError)
		.andDo(print())
		.andExpect(jsonPath("$.error.code",equalTo("E001")))
		.andExpect(jsonPath("$.error.message",equalTo("日期區間不符")))
		.andExpect(status().is(400));
	}

}

```
### 排程 測試
```=java
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

```







## API 測試
---
### API 測試工具 : API Tester



### 1. 測試呼叫查詢美金兌換台幣匯率資料 API，成功並顯示其內容。
![image](https://hackmd.io/_uploads/HkPc2QVF6.png)
![image](https://hackmd.io/_uploads/BJMhhmVYp.png)



### 2. 測試呼叫查詢美金兌換台幣匯率資料 API，失敗並顯示其內容。
![image](https://hackmd.io/_uploads/BkMzp7NY6.png)
![image](https://hackmd.io/_uploads/SyqmpQEYa.png)


