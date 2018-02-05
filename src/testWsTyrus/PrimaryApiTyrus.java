package testWsTyrus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpoint;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

import test.PrimaryAPI;

public class PrimaryApiTyrus {

	private String	activeEndpoint = "ws://localhost:8025/websockets/echo";   
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
            
            client.getProperties().put("X-Auth-Token", api.getToken());
            client.getProperties().put("x-username", api.getUser());
            client.getProperties().put("x-password", api.getPassword());
            client.getProperties().put("cache-control", "no-cache");            
           
			client.connectToServer(new Endpoint() {

				@Override
				public void onOpen(Session session, EndpointConfig config) {
					try {
						session.addMessageHandler(new MessageHandler.Whole<String>() {

							@OnMessage
							public void onMessage(String message) {
								System.out.println(String.format("%s %s", "Received message: ", message));
							}

							@OnOpen
							public void onOpen(Session p) {
								try {
									p.getBasicRemote().sendText("Hello!");
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

							@OnError
							private void onError(Throwable t) {
								System.out.println("OnError Message: ");
								t.printStackTrace();
							}

							@OnClose
							private void onClose( Session session) {
								System.out.println("OnClose Message: ");
								
							}

						});
						System.out.println("Original Message: "+ SENT_MESSAGE);						
						session.getBasicRemote().sendText(SENT_MESSAGE);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, cec, uri);
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
