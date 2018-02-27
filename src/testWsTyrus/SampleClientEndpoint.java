package testWsTyrus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ClientEndpoint(
		configurator = ClientConfigurator.class)
public class SampleClientEndpoint {

	@OnOpen
	public void onOpen(Session p) {
		List<String> futuros = new ArrayList<String>();
		
		futuros.add("DOMar18");
		futuros.add("DOAbr18");
		futuros.add("DOMay18");
		futuros.add("DOJun18");
		futuros.add("DOJul18");
		futuros.add("OROMar18");
		futuros.add("OROMay18");
		futuros.add("OROJul18");

		
		String STR1 = "{\"type\":\"smd\",\"level\":1, \"entries\":[\"BI\", \"OF\"],\"products\":[{\"symbol\":\"";
				
		String STR2 = "\",\"marketId\":\"ROFX\"}]}";

		Iterator<String> nombreIterator = futuros.iterator();
		while(nombreIterator.hasNext()){
			String elemento = nombreIterator.next();
//			System.out.println(STR1+elemento+STR2);
			try {
				p.getBasicRemote().sendText(STR1+elemento+STR2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@OnMessage
	public void onMessage(String message) {
//		System.out.println(String.format("%s %s", "Received message: ", message));
		ObjectMapper objectMapper = new ObjectMapper();
				
		try {
			Answer resp = objectMapper.readValue(message, Answer.class);

			System.out.println(System.currentTimeMillis()+" <- "+objectMapper.writeValueAsString(resp));
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@OnError
	public void processError(Throwable t) {
		t.printStackTrace();
	}
}
