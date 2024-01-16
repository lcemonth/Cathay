package com.cathay.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CurrencyExchangeVo {
	
	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;
	
	@NotBlank
	private String currency;
	
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(String startDate) throws ParseException {
		this.startDate = dateFormat.parse(startDate);
	}
	public Date getEndDate(){
		return endDate;
	}
	public void setEndDate(String endDate) throws ParseException {
		this.endDate = dateFormat.parse(endDate);
	}
    
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return "CurrencyExchangeVo [startDate=" + startDate + ", endDate=" + endDate + ", currency=" + currency + "]";
	}

	
}
