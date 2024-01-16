package com.cathay.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXCHANGE_RATE")
public class ExchangeRateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXCHANGE_ID")
	private Integer exchangeID;
	
	@Column(name = "EXCHANGE_DATE")
	private Date date;
	
	@Column(name = "USD_TO_NTD")
	private Double usdToNtd;	//美元兌換新台幣

	public Integer getExchangeID() {
		return exchangeID;
	}

	public void setExchangeID(Integer exchangeID) {
		this.exchangeID = exchangeID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = new Date(date.getTime());
	}

	public Double getUsdToNtd() {
		return usdToNtd;
	}

	public void setUsdToNtd(Double usdToNtd) {
		this.usdToNtd = usdToNtd;
	}

	@Override
	public String toString() {
		return "ExchangeRateEntity [exchangeID=" + exchangeID + ", date=" + date + ", usdToNtd=" + usdToNtd + "]";
	}





}
