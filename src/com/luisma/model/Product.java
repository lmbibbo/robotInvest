package com.luisma.model;

public class Product {

	private String symbol;
	private String marketId;

	public Product(String symbol) {
		this.symbol = symbol;
		this.marketId = "ROFX";
	}

	public Product() {
	}
	
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
