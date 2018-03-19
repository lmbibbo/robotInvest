package com.luisma.model;

import java.util.List;

public class Message {

	public static final String PREFIX = null;

	private String type;
	private int level;
	private List <String> entries;
	private List <Product> products;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<String> getEntries() {
		return entries;
	}
	public void setEntries(List<String> entries) {
		this.entries = entries;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString()
	{
		return "ClassPojo [level = "+level+", entries = "+entries+", type = "+type+", products = "+products+"]";
	}
}
