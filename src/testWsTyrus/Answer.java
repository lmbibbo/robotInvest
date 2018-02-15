package testWsTyrus;

public class Answer {
	
	private String type = null;
	private Products instrumentId = null;
	private MarketData marketData = null;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Products getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(Products instrumentId) {
		this.instrumentId = instrumentId;
	}
	public MarketData getMarketData() {
		return marketData;
	}
	public void setMarketData(MarketData marketData) {
		this.marketData = marketData;
	}

}
