package testWsTyrus;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;


public class MiEndpoint extends Endpoint {


	private CountDownLatch messageLatch;
    private final String SENT_MESSAGE = "{\"type\":\"Md\",\"instrumentId\":{\"marketId\":\"ROFX\",\"symbol\":\"DONov18\"},\"marketData\":{\"BI\":[{\"price\":22.700,\"size\":500}],\"OF\":[{\"price\":22.900,\"size\":500}]}}";

   
	public MiEndpoint() {
		messageLatch = new CountDownLatch(1);
		
		
	}

  
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		// TODO Auto-generated method stub
        try {
            session.addMessageHandler(new MessageHandler.Whole<String>() {

                @Override
                public void onMessage(String message) {
                    System.out.println("Received message: "+message);
                    messageLatch.countDown();
                }
            });
            session.getBasicRemote().sendText(SENT_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }		
	}

}
