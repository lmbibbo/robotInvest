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
			p.getBasicRemote().sendText("Hello!");
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
