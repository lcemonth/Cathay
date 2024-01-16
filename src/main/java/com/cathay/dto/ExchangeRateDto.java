package com.cathay.dto;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class ExchangeRateDto {

	@SerializedName("Date")
	private Date date;
	
	@SerializedName("USD/NTD")
	private Double usdToNtd;	//美元兌換新台幣
	
	@SerializedName("RMB/NTD")
	private Double rmbToNtd;	//人民幣兌換新台幣	
	
	@SerializedName("EUR/USD")
	private Double eurToUsd;	//歐元兌換美元	
	
	@SerializedName("USD/JPY")
	private Double usdToJpy;	//美元兌換日元
	
	@SerializedName("GBP/USD")
	private Double gbpToUsd;	//英鎊兌換美元	
	
	@SerializedName("AUD/USD")
	private Double audToUsd;	//澳幣兌換美元	
	
	@SerializedName("USD/HKD")
	private Double usdToHkd;	//美元兌換港幣	
	
	@SerializedName("USD/RMB")
	private Double usdToRmb;	//美元兌換人民幣	
	
	@SerializedName("USD/ZAR")
	private Double usdToZar;	//美元兌換南非蘭特	
	
	@SerializedName("NZD/USD")
	private Double nzdToUsd;	//紐西蘭元兌換美元

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getUsdToNtd() {
		return usdToNtd;
	}

	public void setUsdToNtd(Double usdToNtd) {
		this.usdToNtd = usdToNtd;
	}

	public Double getRmbToNtd() {
		return rmbToNtd;
	}

	public void setRmbToNtd(Double rmbToNtd) {
		this.rmbToNtd = rmbToNtd;
	}

	public Double getEurToUsd() {
		return eurToUsd;
	}

	public void setEurToUsd(Double eurToUsd) {
		this.eurToUsd = eurToUsd;
	}

	public Double getUsdToJpy() {
		return usdToJpy;
	}

	public void setUsdToJpy(Double usdToJpy) {
		this.usdToJpy = usdToJpy;
	}

	public Double getGbpToUsd() {
		return gbpToUsd;
	}

	public void setGbpToUsd(Double gbpToUsd) {
		this.gbpToUsd = gbpToUsd;
	}

	public Double getAudToUsd() {
		return audToUsd;
	}

	public void setAudToUsd(Double audToUsd) {
		this.audToUsd = audToUsd;
	}

	public Double getUsdToHkd() {
		return usdToHkd;
	}

	public void setUsdToHkd(Double usdToHkd) {
		this.usdToHkd = usdToHkd;
	}

	public Double getUsdToRmb() {
		return usdToRmb;
	}

	public void setUsdToRmb(Double usdToRmb) {
		this.usdToRmb = usdToRmb;
	}

	public Double getUsdToZar() {
		return usdToZar;
	}

	public void setUsdToZar(Double usdToZar) {
		this.usdToZar = usdToZar;
	}

	public Double getNzdToUsd() {
		return nzdToUsd;
	}

	public void setNzdToUsd(Double nzdToUsd) {
		this.nzdToUsd = nzdToUsd;
	}

	@Override
	public String toString() {
		return "ExchangeRateDto [date=" + date + ", usdToNtd=" + usdToNtd + ", rmbToNtd=" + rmbToNtd + ", eurToUsd="
				+ eurToUsd + ", usdToJpy=" + usdToJpy + ", gbpToUsd=" + gbpToUsd + ", audToUsd=" + audToUsd
				+ ", usdToHkd=" + usdToHkd + ", usdToRmb=" + usdToRmb + ", usdToZar=" + usdToZar + ", nzdToUsd="
				+ nzdToUsd + "]";
	}


}
