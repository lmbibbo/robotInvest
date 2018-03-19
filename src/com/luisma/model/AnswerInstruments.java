package com.luisma.model;

import java.util.List;

public class AnswerInstruments {
	
	private String status;
	private List <Instrument> instruments;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Instrument> getInstruments() {
		return instruments;
	}
	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}
	
	
}
