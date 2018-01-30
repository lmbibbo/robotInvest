package testWs;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

public class PrincipalWs extends WebSocketClient {
	

	private Boolean 	initialized = false;
	private static Boolean 	islogin = false;
	private static String 	user = "user5";
	private static String	password = "password";
	private static String	activeEndpoint = "http://demo-api.primary.com.ar/";
	private String	account = "";
	private static String	token = "";
	private Boolean	verifyHTTPs = false;
	
	private static String	marketID="ROFX";
	private static String	timeInForce="Day";
	
	private static HttpClientContext clientContext;
    private static CloseableHttpClient httpclient; 
    private HttpHost target;
    private static HttpResponse response;
    private RequestConfig config;
	


	public PrincipalWs(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}

	public PrincipalWs(URI serverURI) {
		super(serverURI);
	}

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

	public void login() {

 
	}

	public static void main(String[] args) throws URISyntaxException {
		
		
		WebSocketClient client = new PrincipalWs (new URI( "wss://demo-api.primary.com.ar/" ));
		client.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress( "127.0.0.1", 3128 )));
//		client.setProxy(new InetSocketAddress("127.0.0.1", 3128));
		client.connect();
		
		client.run();
		
		System.out.println(client.getConnection().toString());
	}

}
