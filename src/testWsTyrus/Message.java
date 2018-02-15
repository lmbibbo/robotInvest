package testWsTyrus;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Message {

	public static final String PREFIX = null;

	private String type;
	private int level;
	private List <String> entries;
	private List <Products> products;
	
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
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	
	public void parseJson(String str) {
		JsonObject jsonObject = Json.createReader(new StringReader(str)).readObject();
		
	    
	    this.setType(jsonObject.getString("type"));
	    this.setLevel(jsonObject.getInt("level"));
	    
	    JsonArray jsonEntries = jsonObject.getJsonArray("entries");
	    Iterator<JsonValue> it = jsonEntries.iterator();
	    
	    while(it.hasNext()) {
	    	this.getEntries().add(it.next().toString());
	    }
	    
	    JsonArray jsonProducts = jsonObject.getJsonArray("products");
	    Iterator<JsonValue> itr = jsonProducts.iterator();
	    Products prodToAdd = null;
	    
	    while(itr.hasNext()) {
	    	JsonObject  pro = (JsonObject) itr.next();
	    	prodToAdd = new Products();
	    	prodToAdd.setMarketId(pro.getString("marketId"));
	    	prodToAdd.setSymbol(pro.getString("symbol"));
	    }
	}
	
	@Override
	public String toString()
	{
		return "ClassPojo [level = "+level+", entries = "+entries+", type = "+type+", products = "+products+"]";
	}
}
