package com.cathay.controller;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.config.ExceptionHandlerConfig;
import com.cathay.exception.DateCheckException;
import com.cathay.service.ExchangeRateService;
import com.cathay.vo.CurrencyExchangeVo;
import com.cathay.vo.CurrencyVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ExchangeRate")
@RestController
@RequestMapping("/ExchangeRate/v1")
public class ExchangeRateController {

	static final Logger LOG = LoggerFactory.getLogger(ExchangeRateController.class);
	
	@Autowired
	private ExchangeRateService exchangeRateService;

	@ApiOperation(value = "取得美金兌換台幣匯率", notes = "列出匯率資訊", httpMethod = "POST")
    @PostMapping("/getExchangeRates")
    public ResponseEntity<Map<String,Object>> getUsdToNtdExchangeRateData(@RequestBody @Valid CurrencyExchangeVo currencyExchangeVo) throws ParseException, DateCheckException {

    	LOG.info("CurrencyExchangeVo: " + currencyExchangeVo.toString());
    	
    	Map<String, Object> responseMap = new HashMap<String, Object>();
    	
    	Map<String, String> map = checkDate(currencyExchangeVo);
    	List<CurrencyVo> currencyVoList= exchangeRateService.findByDateBetweenUsdToNtd(currencyExchangeVo);
    	
    	responseMap.put("error", map);
    	responseMap.put("currency", currencyVoList);
    	
    	LOG.info("responseMap: " + responseMap.toString());
    	
    	return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }
    
    /**
     * 檢查日期
     * 1.開始日期不得大於結束日期
     * 2.檢查 開始日期 是否超過一年
     * 3.檢查 結束日期 當天日期-1天
     * */
    private Map<String, String> checkDate(CurrencyExchangeVo currencyExchangeVo) throws DateCheckException {
		LocalDate startDate = dateToLocalDate(currencyExchangeVo.getStartDate());
		LocalDate endDate = dateToLocalDate(currencyExchangeVo.getEndDate());
		LocalDate nowDate = LocalDate.now();
		
		Map<String, String> resMap = new HashMap<String, String>();
		resMap.put("code", "0000");
		resMap.put("message", "成功");
		
		//開始日期不得大於結束日期
		if(startDate.compareTo(endDate) > 0) {
			resMap.put("code", "E001");
			resMap.put("message", "開始日期不得大於結束日期");
			
			throw new DateCheckException(resMap);
		}
		
		//檢查 開始日期 是否超過一年
		if(startDate.compareTo(nowDate.minusYears(1)) < 0) {
			resMap.put("code", "E001");
			resMap.put("message", "日期區間不符");
			
			throw new DateCheckException(resMap);
		}
		
		//檢查 結束日期 當天日期-1天
		if(endDate.compareTo(nowDate.minusDays(1)) > 0) {
			resMap.put("code", "E001");
			resMap.put("message", "日期區間不符");
			
			throw new DateCheckException(resMap);
		}
    	
		return resMap; 
    }
    private LocalDate dateToLocalDate(Date date) {
    	Instant instant = date.toInstant();
    	LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
    	
    	return localDate;
    }
}
