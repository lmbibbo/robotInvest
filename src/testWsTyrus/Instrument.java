package testWsTyrus;

public class Instrument {
	
	private String marketId="ROFX";
	private String symbol="";
	private String cficode="";

	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCficode() {
		return cficode;
	}
	public void setCficode(String cficode) {
		this.cficode = cficode;
	}
	
}
