package testWsTyrus;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ClientEndpointConfig.Configurator;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;
import org.glassfish.tyrus.container.grizzly.client.GrizzlyClientSocket;

public class DocClient {
    private static CountDownLatch messageLatch;
    private static final String SENT_MESSAGE = "{type:Md,instrumentId:{marketId:ROFX,symbol:DONov18},marketData:{BI:[{price:22.700,size:500}],OF:[{price:22.900,size:500}]}}";
//	private static String	activeEndpoint = "ws://localhost:8025/websockets/echo";   
	private static String	activeEndpoint = "ws://demo-api.primary.com.ar/";      

    public static void main(String [] args){
    	
    	
    	try {
    
    		messageLatch = new CountDownLatch(1);

    		Configurator miClientEndpointConfig = new ClientConfigurator();

            final ClientEndpointConfig cec = ClientEndpointConfig
            		.Builder
            		.create()
//            		.configurator(miClientEndpointConfig)
            		.build();
            
            ClientManager client = ClientManager.createClient();
            
//            client.getProperties().put(ClientProperties.PROXY_URI, "http://127.0.0.1:8010");
//            client.getProperties().put(ClientProperties.LOG_HTTP_UPGRADE, true);
            
//            client.getProperties().put(GrizzlyClientSocket.PROXY_URI, "http://127.0.0.1:8010");
            
//            client.getProperties().put(GrizzlyClientSocket.PROXY_URI, "http://127.0.0.1:8010");
            
            client.connectToServer(SampleClientEndpoint.class, new URI(activeEndpoint));

            messageLatch.await(50, TimeUnit.SECONDS);
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
            }, cec, new URI(activeEndpoint));
            messageLatch.await(100, TimeUnit.SECONDS);
            
            System.out.println(client.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
