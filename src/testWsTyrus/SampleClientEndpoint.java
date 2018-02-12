package testWsTyrus;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint(
		decoders = SampleDecoder.class,
		encoders = SampleEncoder.class,
		subprotocols = {"subprtotocol1", "subprotocol2"},
		configurator = ClientConfigurator.class)
public class SampleClientEndpoint {

	@OnOpen
	public void onOpen(Session p) {
		try {
		    String SENT_MESSAGE = "{\"type\":\"smd\",\"level\":1, \"entries\":[\"BI\", \"OF\"],\"products\":[{\"symbol\":\"DONov18\",\"marketId\":\"ROFX\"}]}";
		   
			p.getBasicRemote().sendText(SENT_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println(String.format("%s %s", "Received message: ", message));
	}
	
	@OnError
	public void processError(Throwable t) {
		t.printStackTrace();
	}
}
