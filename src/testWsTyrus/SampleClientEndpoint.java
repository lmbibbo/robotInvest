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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ClientEndpoint(
		configurator = ClientConfigurator.class)
public class SampleClientEndpoint {

	@OnOpen
	public void onOpen(Session p) {
		
		ArrayList<Call> llamados = new ArrayList<Call>();
		List<String> futuros = new ArrayList<String>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		futuros.add("DOMar18");
		futuros.add("DOAbr18");
		futuros.add("DOMay18");
		futuros.add("DOJun18");
		futuros.add("DOJul18");
		futuros.add("OROMar18");
		futuros.add("OROMay18");
		futuros.add("OROJul18");

		Iterator<String> nombreIterator = futuros.iterator();
		while(nombreIterator.hasNext()){
			String elemento = nombreIterator.next();

			Call elem = new Call();
			elem.getProducts().add(new Products(elemento));

			try {
				System.out.println(objectMapper.writeValueAsString(elem));
				p.getBasicRemote().sendText(objectMapper.writeValueAsString(elem));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@OnMessage
	public void onMessage(String message) {

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

//		System.out.println(message);
	}
	
	@OnError
	public void processError(Throwable t) {
		t.printStackTrace();
	}
}
