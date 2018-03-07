package testWsTyrus;

public class Products {

	public Products(String symbol) {
		super();
		this.symbol = symbol;
		this.marketId = "ROFX";
	}

	private String symbol;
	private String marketId;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
    
	@Override
    public String toString()
    {
        return "ClassPojo [marketId = "+marketId+", symbol = "+symbol+"]";
    }
}
