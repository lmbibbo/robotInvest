package testWsTyrus;

import java.util.ArrayList;

public class Call {

	private String type = null;
	private int level = 1;
	private ArrayList<String> entries = null;
	private ArrayList<Products> products = null;
	
	public Call() {
		ArrayList<String> entr= new ArrayList<String>();
		entr.add("BI");
		entr.add("OF");
		ArrayList<Products> prod=new ArrayList<Products>();
		
		this.init("smd", 1, entr, prod );
	}
	
	private void init(String type, int level, ArrayList<String> entries, ArrayList<Products> products) {
		this.type = type;
		this.level = level;
		this.entries = entries;
		this.products = products;
	}
	
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
	public ArrayList<String> getEntries() {
		return entries;
	}
	public void setEntries(ArrayList<String> entries) {
		this.entries = entries;
	}
	public ArrayList<Products> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Products> products) {
		this.products = products;
	}
}
