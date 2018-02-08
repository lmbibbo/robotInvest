package testWsTyrus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
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
import javax.websocket.ClientEndpointConfig.Configurator;
import javax.websocket.CloseReason;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;
import org.glassfish.tyrus.core.TyrusFuture;

import test.PrimaryAPI;

public class PrimaryApiTyrus {

	private String	activeEndpoint = "ws://localhost:8025/websockets/echo";   
//	private String	activeEndpoint = "ws://demo-api.primary.com.ar/";   
//	private ClientEndpointConfig cec=ClientEndpointConfig.Builder.create().build();
//    private ClientManager client = ClientManager.createClient();
//   private String SENT_MESSAGE = "Hello World";
    private CountDownLatch messageLatch;

     
	public PrimaryApiTyrus() {
		// TODO Auto-generated constructor stub
	}

	public void connect() {
			
		try {

			messageLatch = new CountDownLatch(1);

			Configurator miClientEndpointConfig = new ClientConfigurator();

			final ClientEndpointConfig cec = ClientEndpointConfig
					.Builder
					.create()
					.configurator(miClientEndpointConfig)
					.build();

			ClientManager client = ClientManager.createClient();

//			client.getProperties().put(ClientProperties.PROXY_URI, "http://127.0.0.1:8010");
//			client.getProperties().put(ClientProperties.LOG_HTTP_UPGRADE, true);

//	        client.getProperties().put(GrizzlyClientSocket.PROXY_URI, "http://127.0.0.1:8010");

			ClientManager.ReconnectHandler reconnectHandler = new ClientManager.ReconnectHandler() {

				private int counter = 0;

				@Override
				public boolean onDisconnect(CloseReason closeReason) {
					counter++;
					if (counter <= 3) {
						System.out.println("### Reconnecting... (reconnect count: " + counter + ")");
						return true;
					} else {
						return false;
					}
				}

				@Override
				public boolean onConnectFailure(Exception exception) {
					counter++;
					if (counter <= 3) {
						System.out.println("### Reconnecting... (reconnect count: " + counter + ") " + exception.getMessage());

						// Thread.sleep(...) or something other "sleep-like" expression can be put here - you might want
						// to do it here to avoid potential DDoS when you don't limit number of reconnects.
						return true;
					} else {
						return false;
					}
				}

				@Override
				public long getDelay() {
					return 1;
				}
			};

			client.getProperties().put(ClientProperties.RECONNECT_HANDLER, reconnectHandler);

			
			
			client.connectToServer(new Endpoint() {

				@OnOpen
				public void onOpen(Session session) throws IOException {
					session.getBasicRemote().sendText("onOpen");
					System.out.println("Paso por: onOpen");
				}

				@OnMessage
				public String echo(String message) {
					System.out.println("Paso por: Message");
					return message + " (from your server)";
				}

				@OnError
				public void onError(Throwable t) {
					t.printStackTrace();
				}

				@OnClose
				public void onClose(Session session) {
					System.out.println("Paso por: onOClose");
				}

				@Override
				public void onOpen(Session session, EndpointConfig config) {
					// TODO Auto-generated method stub
					try {
						session.getBasicRemote().sendText("onOpen con Config");
						System.out.println("Paso por: onOpen con Config");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			}, cec, new URI(activeEndpoint)); 
			messageLatch.await(1, TimeUnit.SECONDS);

			System.out.println(client.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
