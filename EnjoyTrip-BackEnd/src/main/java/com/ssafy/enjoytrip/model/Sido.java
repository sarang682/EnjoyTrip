package com.ssafy.enjoytrip.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Sido {
	
	private int sidoCode;
	private String sidoName;
	
	public int getSidoCode() {
		return sidoCode;
	}
	
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	
	public String getSidoName() {
		return sidoName;
	}
	
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	
}
