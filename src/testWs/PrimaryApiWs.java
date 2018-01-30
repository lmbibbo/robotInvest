package testWs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import test.DetectProxy;
import test.PrimaryAPI;

public class PrimaryApiWs {

	private String	activeEndpoint = "ws://demo-api.primary.com.ar:8081/pbcp/";   
    private PrimaryAPI api;
	private WebSocketClient client;    
    private Map<String,String> httpHeaders = new HashMap<String, String>();
   
	public PrimaryApiWs() {
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
			try {
				client.connectBlocking();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		

		httpHeaders.put("x-username", api.getUser());
		httpHeaders.put("x-password", api.getPassword());
		httpHeaders.put("cache-control", "no-cache");
		httpHeaders.put("X-Auth-Token", api.getToken());
		
		client = new  WebSocketClient(uri, httpHeaders) {
			
			@Override
			public void onOpen(ServerHandshake handshakedata) {
				send("Hello, it is me. Mario :)");
				System.out.println("new connection opened");
			}

			@Override
			public void onClose(int code, String reason, boolean remote) {
				System.out.println("closed with exit code " + code + " additional info: " + reason);
			}

			@Override
			public void onMessage(String message) {
				System.out.println("received message: " + message);
			}

			@Override
			public void onMessage(ByteBuffer message) {
				System.out.println("received ByteBuffer");
			}

			@Override
			public void onError(Exception ex) {
				System.err.println("an error occurred:" + ex);
			}
	
		};
		
		DetectProxy detect = new DetectProxy();
    	if (detect.isHasProxy()) {
    		client.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress( "127.0.0.1", 3128 )));
    	}
    	
	}
	
}
