package com.luisma.model;

public class Instrument {
	
	private Product instrumentId;
	private String cficode="";

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
	
}
