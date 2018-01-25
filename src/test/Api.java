package test;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class api {

	private static String wsEndpointDemo = "ws://demo-api.primary.com.ar/";
	
	private static Boolean initialized = false;
	private static Boolean islogin = false;
	private static String user = "user5";
	private static String		password = "password";
	private static String		activeEndpoint = "http://demo-api.primary.com.ar/";
	private static String		activeWSEndpoint = "";
	private static String			account = "";
	private static String		token = "";
	private static Boolean		verifyHTTPs = false;
	
	
	private static String	marketID="ROFX";
	private static String	timeInForce="Day";
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub

    	DetectProxy detect = new DetectProxy();
        HttpHost proxy = new HttpHost(detect.getHostProxy(), detect.getPort(), detect.getProxyType());
		
        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .build();
        
    	HttpUriRequest request = RequestBuilder
    			.post("http://demo-api.primary.com.ar/auth/getToken")
				.addHeader("x-username", "user5")
		    	.addHeader("x-password", "password")
		    	.addHeader("cache-control", "no-cache")
		    	.setConfig(config)
		    	.build();
        
        HttpHost target = new HttpHost("http://demo-api.primary.com.ar/", 80, "http");
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        CloseableHttpResponse response = httpclient.execute(target, request);
        
	}

	
	
	private static boolean islogin() {
		return initialized;
		
	/*
	    if(! islogin) {
	    	
	    	HttpResponse<String> response = Unirest.post("http://demo-api.primary.com.ar/auth/getToken")
	    			  .header("x-username", "user5")
	    			  .header("x-password", "password")
	    			  .header("cache-control", "no-cache")
	    			  .header("postman-token", "4bd8f181-2bca-7bd4-d47e-304387121aad")
	    			  .asString();
	    	
	    	CloseableHttpClient client = HttpClients.createDefault();
	    	
	    	client.
	    	
	    	Request request = new Request.Builder()
	    	  .url("http://demo-api.primary.com.ar/auth/getToken")
	    	  .post(null)
	    	  .addHeader("x-username", "user5")
	    	  .addHeader("x-password", "password")
	    	  .addHeader("cache-control", "no-cache")
	    	  .build();

	    	Response response = client.newCall(request).execute();	    	
	    	
	    }

	        # Checkeamos si la respuesta del request fue correcta, un ok va a ser un response code 200 (OK)
	        if(loginResponse.ok):        
	            token = loginResponse.headers['X-Auth-Token'];
	            success = True
	        else:   
	            print("\nRequest Error.")
	            success = False
	        islogin=success   
	    else:
	        print ("Ya estamos logueados")
	        success = True
	    return success        
		
		return initialized;
	*/	
	}
	
}
