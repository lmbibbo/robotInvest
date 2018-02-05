package testWsTyrus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpoint;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.glassfish.tyrus.client.ClientManager;

import test.PrimaryAPI;

public class PrimaryApiTyrus {

//	private String	activeEndpoint = "ws://localhost:8025/websockets/echo";   
	private String	activeEndpoint = "ws://demo-api.primary.com.ar/";   
	private ClientEndpointConfig cec=ClientEndpointConfig.Builder.create().build();
    private ClientManager client = ClientManager.createClient();
    private PrimaryAPI api;
    private String SENT_MESSAGE = "Hello World";
    private CountDownLatch messageLatch;

     
	public PrimaryApiTyrus() {
		// TODO Auto-generated constructor stub
		api = new PrimaryAPI();
	}

	public void login() {
 		if (! api.Islogin()) {
			api.login();
		}
	}

	public void connect() {
		if (api.Islogin()) {
			init();
		}
	}

	private void init() {
		URI uri = null;
		try {
			uri = new URI (activeEndpoint);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			messageLatch = new CountDownLatch(1);
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			
			container.connectToServer(SampleClientEndpoint.class, uri);


			messageLatch.await(100, TimeUnit.SECONDS);
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
