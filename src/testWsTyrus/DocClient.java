package testWsTyrus;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ClientEndpointConfig.Configurator;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Extension;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;

public class DocClient {
    private static CountDownLatch messageLatch;
    private static final String SENT_MESSAGE = "Hello World";
    

    public static void main(String [] args){
    	
    	
    	try {
    
    		messageLatch = new CountDownLatch(1);

    		Configurator miClientEndpointConfig = new ClientConfigurator();

            final ClientEndpointConfig cec = ClientEndpointConfig
            		.Builder
            		.create()
            		.configurator(miClientEndpointConfig)
            		.build();
            
//            cec.getUserProperties().put("x-username", "user5");
//            cec.getUserProperties().put("x-password", "password");
//            cec.getUserProperties().put("cache-control", "no-cache");
   
            ClientManager client = ClientManager.createClient();
            client.getProperties().put(ClientProperties.PROXY_URI, "http://127.0.0.1:8010");
            
            client.connectToServer(new Endpoint() {

                @Override
                public void onOpen(Session session, EndpointConfig config) {
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
            }, cec, new URI("ws://demo-api.primary.com.ar/"));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
