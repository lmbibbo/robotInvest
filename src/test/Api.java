package test;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.List;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Api {


	public Api() {

	}

	private static String wsEndpointDemo = "ws://demo-api.primary.com.ar/";
	
	private static Boolean 	initialized = false;
	private static Boolean 	islogin = false;
	private static String 	user = "user5";
	private static String	password = "password";
	private static String	activeEndpoint = "http://demo-api.primary.com.ar/";
	private static String	activeWSEndpoint = "";
	private static String	account = "";
	private static String	token = "";
	private static Boolean	verifyHTTPs = false;
	
	
	private static String	marketID="ROFX";
	private static String	timeInForce="Day";
	

	public static void main(String[] args) throws ClientProtocolException, IOException  {
		
	
		login2();
		
		
	}
	
	public boolean login() throws ClientProtocolException, IOException {
    	
		if (! islogin) {
			DetectProxy detect = new DetectProxy();
	    	HttpUriRequest request= null;
	    	if (detect.isHasProxy()) {
	            HttpHost proxy = new HttpHost(detect.getHostProxy(), detect.getPort(), detect.getProxyType());
	    		
	            RequestConfig config = RequestConfig.custom()
	                    .setProxy(proxy)
	                    .build();
	            
	        	request = RequestBuilder
	        			.post("http://demo-api.primary.com.ar/auth/getToken")
	    				.addHeader("x-username", user)
	    		    	.addHeader("x-password", password)
	    		    	.addHeader("cache-control", "no-cache")
	    		    	.setConfig(config)
	    		    	.build();
	    	}
	    	else {
	        	request = RequestBuilder
	        			.post("http://demo-api.primary.com.ar/auth/getToken")
	    				.addHeader("x-username", user)
	    		    	.addHeader("x-password", password)
	    		    	.addHeader("cache-control", "no-cache")
	    		    	.build();
	    	}
	        
	        HttpHost target = new HttpHost("http://demo-api.primary.com.ar/", 80, "http");
	        CloseableHttpClient httpclient = HttpClients.createDefault();  
	        CloseableHttpResponse response = httpclient.execute(target, request);
	        
	        //token=response.getHeaders("X-Auth-Token").toString();
	        HeaderElementIterator it = new BasicHeaderElementIterator(
	        	    response.headerIterator("X-Auth-Token"));

	        while (it.hasNext()) {
	        	token=it.nextElement().getName();
	            islogin=true;
	        }
		}
        return islogin;
		
	}

	public static boolean login2(){
    	
		HttpContext context = new BasicHttpContext();
		HttpClientContext clientContext = HttpClientContext.adapt(context);
		HttpHost target = clientContext.getTargetHost();
		HttpResponse response = clientContext.getResponse();
		RequestConfig config = clientContext.getRequestConfig();
		DetectProxy detect = new DetectProxy();

    	if (detect.isHasProxy()) {
            HttpHost proxy = new HttpHost(detect.getHostProxy(), detect.getPort(), detect.getProxyType());
            RequestConfig proxyConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            clientContext.setRequestConfig(proxyConfig);
    	}
    	HttpRequest request = clientContext.getRequest();
		
        CloseableHttpClient httpclient = HttpClients.createDefault(); 
        try {
    		if (! islogin) {
    			
    			HttpPost post = new HttpPost("http://demo-api.primary.com.ar/auth/getToken");
    			post.addHeader("x-username", user);
    	    	post.addHeader("x-password", password);
    	    	post.addHeader("cache-control", "no-cache");

    			response = httpclient.execute(post, clientContext);
    	        
    	        HeaderElementIterator it = new BasicHeaderElementIterator(
    	        	    response.headerIterator("X-Auth-Token"));

    	        while (it.hasNext()) {
    	        	token=it.nextElement().getName();
    	            islogin=true;
    	        }

    	        if (islogin) {
    				HttpGet gets = new HttpGet("http://demo-api.primary.com.ar/rest/segment/all");
    				post.addHeader("X-Auth-Token", token);
    		    	
    		    	response = httpclient.execute(gets, clientContext);
    		    	
    		    	if (response.getStatusLine().getStatusCode() == 200)
    		    	 {     
    		            String result = EntityUtils.toString(response.getEntity());
    		            JSONObject obj;
						try {
							obj = new JSONObject(result);
	    		            System.out.println(obj.toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  

    		    	 }
 
    	        }
    	        
    		}
        	
        } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        finally {
        	try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        return islogin;
		
	}

	private static void requestPost(String url) throws ClientProtocolException, IOException {

		if (islogin) {
			DetectProxy detect = new DetectProxy();
	    	HttpUriRequest request= null;
	    	if (detect.isHasProxy()) {
	            HttpHost proxy = new HttpHost(detect.getHostProxy(), detect.getPort(), detect.getProxyType());
	    		
	            RequestConfig config = RequestConfig.custom()
	                    .setProxy(proxy)
	                    .build();
	            
	        	request = RequestBuilder
	        			.post(url)
	    				.addHeader("X-Auth-Token", token)
	    		    	.setConfig(config)
	    		    	.build();
	    	}
	    	else {
	        	request = RequestBuilder
	        			.post(url)
	    				.addHeader("X-Auth-Token", token)
	    		    	.build();
	    	}
	        
	        HttpHost target = new HttpHost(url, 80, "http");
	        CloseableHttpClient httpclient = HttpClients.createDefault();  
	        CloseableHttpResponse response = httpclient.execute(target, request);
	        
	        HeaderIterator it = response.headerIterator();

	        while (it.hasNext()) {
	            System.out.println(it.next());
	        }
		}
		else {
            System.out.println("Usuario No Logueado");
		}
	
		
	}
	
	public void getInstrumentos() throws ClientProtocolException, IOException {
		
		login();
		requestPost(activeEndpoint + "rest/instruments/all");

		
	}
	
}
