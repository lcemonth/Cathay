package com.cathay.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.cathay.dto.ExchangeRateDto;
import com.cathay.vo.CurrencyExchangeVo;
import com.cathay.vo.CurrencyVo;

public interface ExchangeRateService {
	
	/**
	 * 排成寫入每日匯率資料
	 * @param List<ExchangeRateDto> 每日匯率資料
	 * 
	 * */
	public Integer addExchangeRate(List<ExchangeRateDto> exchangeRateList);
	
	/**
	 * 查詢美金兌換台幣匯率
	 * @param CurrencyExchangeVo 查詢條件
	 * 
	 * @return List<UsdExchangeRateVo> 返回 美金兌換台幣匯率 資料
	 * */
	public List<CurrencyVo> findByDateBetweenUsdToNtd (CurrencyExchangeVo currencyExchangeVo) throws ParseException;
}
