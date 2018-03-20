package com.luisma.model;

public class AnswerInstrumentDetail {
	
	private String status;
	private Product instrumentId;
	private String cficode;
	private String currency;
	private double minPriceIncrement;
		
	public String getCficode() {
		return cficode;
	}
	public void setCficode(String cficode) {
		this.cficode = cficode;
	}
	public Product getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(Product instrumentId) {
		this.instrumentId = instrumentId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getMinPriceIncrement() {
		return minPriceIncrement;
	}
	public void setMinPriceIncrement(double minPriceIncrement) {
		this.minPriceIncrement = minPriceIncrement;
	}

}
