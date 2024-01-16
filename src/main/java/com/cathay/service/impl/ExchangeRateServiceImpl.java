package com.cathay.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cathay.config.ExceptionHandlerConfig;
import com.cathay.dao.ExchangeRateDao;
import com.cathay.dto.ExchangeRateDto;
import com.cathay.entity.ExchangeRateEntity;
import com.cathay.service.ExchangeRateService;
import com.cathay.vo.CurrencyExchangeVo;
import com.cathay.vo.CurrencyVo;

@Component
public class ExchangeRateServiceImpl implements ExchangeRateService {

	static final Logger LOG = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);
	
	@Autowired
	private ExchangeRateDao exchangeRateDao;

	@Transactional
	@Override
	public Integer addExchangeRate(List<ExchangeRateDto> exchangeRateList) {

		LOG.info("exchangeRateList: " + exchangeRateList.toString());
		
		//VO copyProperties Entity
		List<ExchangeRateEntity> exchangeRateEntityList = copyPropertiesEntity(exchangeRateList);

		//insert Data
		LOG.info("ExchangeRateEntityList: "+ exchangeRateEntityList.toString());
		return exchangeRateDao.saveAll(exchangeRateEntityList).size();
	}
    
	
	@Override
	public List<CurrencyVo> findByDateBetweenUsdToNtd(CurrencyExchangeVo currencyExchangeVo) throws ParseException {
		// TODO Auto-generated method stub
		
		LOG.info("currencyExchangeVo: " + currencyExchangeVo.toString());
		
		Date startDate = new java.sql.Date(currencyExchangeVo.getStartDate().getTime());
		Date endDate = new java.sql.Date(currencyExchangeVo.getEndDate().getTime());
        String currency = currencyExchangeVo.getCurrency();	//查詢哪種幣值，此需求只對美金/台幣，故暫無使用
		
        //select Date
        List<ExchangeRateEntity> exchangeRateList = exchangeRateDao.findByDateBetween(startDate, endDate);
		
        //Entity copyProperties Vo
        List<CurrencyVo> currencyVoList = new ArrayList<CurrencyVo>();
        
        for(ExchangeRateEntity entity : exchangeRateList) {
        	CurrencyVo vo = new CurrencyVo();
    		BeanUtils.copyProperties(entity, vo);
    		vo.setUsd(entity.getUsdToNtd());

    		currencyVoList.add(vo);
        }


		LOG.info("currencyVoList: " + currencyVoList.toString());
		return currencyVoList;
	}
	
	
    /**
     * 將POJO copyProperties Entity
     * 
     * */
    private <T> List<ExchangeRateEntity> copyPropertiesEntity(List<T> copyPropertiesList) {
    	List<ExchangeRateEntity> tList = new ArrayList<ExchangeRateEntity>();
    	
    	for (T pojo : copyPropertiesList) {
    		ExchangeRateEntity entity = new ExchangeRateEntity();
    		
    		BeanUtils.copyProperties(pojo, entity);
    		tList.add(entity);
    	}
    	return tList;
    }
	
}
