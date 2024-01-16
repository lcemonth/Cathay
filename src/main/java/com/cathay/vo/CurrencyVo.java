package com.cathay.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class CurrencyVo {
	private String date;
	private Double usd;	//美元兌換新台幣
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	public String getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = dateFormat.format(date);
	}
	public Double getUsd() {
		return usd;
	}
	public void setUsd(Double usd) {
		this.usd = usd;
	}
	@Override
	public String toString() {
		return "CurrencyVo [date=" + date + ", usd=" + usd + "]";
	}
}
