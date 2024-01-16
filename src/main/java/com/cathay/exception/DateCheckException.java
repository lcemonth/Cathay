package com.cathay.exception;

import java.util.HashMap;
import java.util.Map;

public class DateCheckException extends Exception{
	
	Map<String, String> resMap = new HashMap<String, String>();
	
	public DateCheckException(Map<String, String> resMap) {
		this.resMap = resMap;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return resMap.toString();
	}

	public Map<String, String> getResMap() {
		return resMap;
	}

	public void setResMap(Map<String, String> resMap) {
		this.resMap = resMap;
	}

}
