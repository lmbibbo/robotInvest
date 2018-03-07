package testWsTyrus;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.glassfish.tyrus.client.ClientManager;

public class PrimaryApiTyrus {

	private String	activeEndpoint = "ws://demo-api.primary.com.ar/";   
    private CountDownLatch messageLatch;
     
	public PrimaryApiTyrus() {
		// TODO Auto-generated constructor stub
	}

	public void connect() {
    	try {
    		messageLatch = new CountDownLatch(1);
         
            ClientManager client = ClientManager.createClient();
            
            client.connectToServer(SampleClientEndpoint.class, new URI(activeEndpoint));

            messageLatch.await(10, TimeUnit.MINUTES);
            
            System.out.println(client.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
