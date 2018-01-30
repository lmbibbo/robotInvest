package test;

import java.io.IOException;

import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class PrimaryAPI {

	private Boolean 	initialized = false;
	private Boolean 	islogin = false;
	private String 	user = "user5";
	private String	password = "password";
	private static String	activeEndpoint = "http://demo-api.primary.com.ar/";
	private String	account = "";
	private String	token = "";
	private Boolean	verifyHTTPs = false;
	
	private static String	marketID="ROFX";
	private static String	timeInForce="Day";
	
	private HttpClientContext clientContext;
    private CloseableHttpClient httpclient; 
    private HttpHost target;
    private HttpResponse response;
    private RequestConfig config;
	
	public PrimaryAPI() {
		HttpContext context = new BasicHttpContext();
		clientContext = HttpClientContext.adapt(context);
        httpclient = HttpClients.createDefault(); 
		target = clientContext.getTargetHost();
		response = clientContext.getResponse();
		config = clientContext.getRequestConfig();
		DetectProxy detect = new DetectProxy();

    	if (detect.isHasProxy()) {
            HttpHost proxy = new HttpHost(detect.getHostProxy(), detect.getPort(), detect.getProxyType());
            RequestConfig proxyConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            clientContext.setRequestConfig(proxyConfig);
    	}
    	
    	initialized=true;
	}

	public void login() {

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
    		}
        	
        } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public String getSegmentos() {
        String url = activeEndpoint+"/rest/segment/all";
        return getUrl(url);
	}
	
	public String getInstrumentos() {
        String url = activeEndpoint+"/rest/instruments/all";
        return getUrl(url);
	}
	
	public String getInstrumentosDetail() {
        String url = activeEndpoint+"/rest/instruments/details";
        return getUrl(url);
	}

	public String getInstrumentosDetail(String marketId, String symbol) {
        String url = activeEndpoint+"/rest/instruments/detail?symbol="+marketId+"&marketId="+symbol;
        return getUrl(url);
	}
	

	private String getUrl(String url)  {

        String result = "nada";
        try {
        	if (islogin) {
        		HttpGet gets = new HttpGet(url);
//        		gets.addHeader("X-Auth-Token", token);
        		response = httpclient.execute(gets, clientContext);
        		if (response.getStatusLine().getStatusCode() == 200)
        		{     
        			result = EntityUtils.toString(response.getEntity());
        		}
        	}
        } catch (ClientProtocolException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        } catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		} 
		return result;
	}

	public Boolean Initialized() {
		return initialized;
	}

	public void finalize() {
		try {
			httpclient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			super.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Boolean Islogin() {
		return islogin;
	}

	
}
