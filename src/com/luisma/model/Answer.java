package com.luisma.model;

public class Answer {
	
	private String type = null;
	private Product instrumentId = null;
	private MarketData marketData = null;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Product getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(Product instrumentId) {
		this.instrumentId = instrumentId;
	}
	public MarketData getMarketData() {
		return marketData;
	}
	public void setMarketData(MarketData marketData) {
		this.marketData = marketData;
	}

}
