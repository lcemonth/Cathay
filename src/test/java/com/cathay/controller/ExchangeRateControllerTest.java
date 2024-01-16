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
