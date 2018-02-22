package testWsTyrus;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ClientEndpointConfig.Configurator;

import org.glassfish.tyrus.client.ClientManager;

public class DocClient {
    private static CountDownLatch messageLatch;

//	private static String	activeEndpoint = "ws://localhost:8025/websockets/echo";   
	private static String	activeEndpoint = "ws://demo-api.primary.com.ar/";      

    public static void main(String [] args){
    	
    	
    	try {
    
    		messageLatch = new CountDownLatch(1);
         
            ClientManager client = ClientManager.createClient();
            
//            client.getProperties().put(ClientProperties.PROXY_URI, "http://127.0.0.1:8010");
//            client.getProperties().put(ClientProperties.LOG_HTTP_UPGRADE, true);
          
            client.connectToServer(SampleClientEndpoint.class, new URI(activeEndpoint));

            messageLatch.await(10, TimeUnit.MINUTES);
            
            System.out.println(client.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
