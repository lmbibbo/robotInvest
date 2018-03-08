package com.luisma.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonSetter;

public class MarketData {
	
	private ArrayList<Values> bi = null;
	private ArrayList<Values> of = null;

	public ArrayList<Values> getBi() {
		return bi;
	}

	@JsonSetter("BI")
	public void setBi(ArrayList<Values> bi) {
		this.bi = bi;
	}
	public ArrayList<Values> getOf() {
		return of;
	}
    @JsonSetter("OF")
	public void setOf(ArrayList<Values> of) {
		this.of = of;
	}

    @Override
    public String toString() {
    	return "BI:"+bi.toString()+ ",OF:"+of.toString();
    	
    }
}
