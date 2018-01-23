package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class principal {

	private final static String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws ClientProtocolException, IOException  {
		// TODO Auto-generated method stub
		ej119();

	}
	
	private static void ej119() throws ClientProtocolException, IOException {
		String url = "http://demo-api.primary.com.ar/login.html";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("Response Code : "
		                + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
			System.out.println(line);
		}
	}
	
	private static void ej118() throws ClientProtocolException, IOException {
		
		String url = "http://www.google.com/search?q=httpClient";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : "
		                + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
			System.out.println(line);
		}
		
	}
	
	private static void ej117() {
		File file = new File("somefile.txt");
		FileEntity entity = new FileEntity(file, 
		    ContentType.create("text/plain", "UTF-8"));        

		HttpPost httppost = new HttpPost("http://www.google.com");
		httppost.setEntity(entity);
	}
	
	private static void ej116() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://www.google.com");
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
		    HttpEntity entity = response.getEntity();
		    if (entity != null) {
		        long len = entity.getContentLength();
		        if (len != -1 && len < 2048) {
		            System.out.println(EntityUtils.toString(entity));
		        } else {
		            // Stream content out
		        }
		    }
		} finally {
		    response.close();
		}
	}
	
	private static void ej115() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://www.google.com");
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
		    HttpEntity entity = response.getEntity();
		    if (entity != null) {
		        InputStream instream = entity.getContent();
		        int byteOne = instream.read();
		        int byteTwo = instream.read();
		        // Do not need the rest
		        
		        System.out.println("byteOne: " +byteOne+ " byteTwo: " +byteTwo);		
		    }
		} finally {
		    response.close();
		}
	}
	
	private static void ej113() {
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
			response.addHeader("Set-Cookie", 
			    "c1=a; path=/; domain=localhost");
			response.addHeader("Set-Cookie", 
			    "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
			Header h1 = response.getFirstHeader("Set-Cookie");
			System.out.println(h1);
			Header h2 = response.getLastHeader("Set-Cookie");
			System.out.println(h2);
			Header[] hs = response.getHeaders("Set-Cookie");
			System.out.println(hs.length);		
	}
	
	private static void ej1( ) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://www.pagina12.com.ar/");
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
		   
		} finally {
		    response.close();
		}
	}

	private static URI getUri() throws URISyntaxException {
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost("www.google.com")
		        .setPath("/search")
		        .setParameter("q", "httpclient")
		        .setParameter("btnG", "Google Search")
		        .setParameter("aq", "f")
		        .setParameter("oq", "")
		        .build();
		return uri;
	}
}
