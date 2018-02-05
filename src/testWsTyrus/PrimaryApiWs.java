package testWsTyrus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

public class PrimaryApiWs {
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
	
	
	public class Authenticator implements ClientRequestFilter {

	    private final String user1;
	    private final String password1;

	    public Authenticator(String user, String password) {
	        this.user1 = user;
	        this.password1 = password;
	    }

		@Override
	    public void filter(ClientRequestContext requestContext) throws IOException {
	        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
	        final String basicAuthentication = getBasicAuthentication();
	        headers.add("Authorization", basicAuthentication);

	    }

	    private String getBasicAuthentication() {
	        String token = this.user1 + ":" + this.password1;
	        try {
	            return "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
	        } catch (UnsupportedEncodingException ex) {
	            throw new IllegalStateException("Cannot encode with UTF-8", ex);
	        }
	    }

	}

	public PrimaryApiWs() {   
	}

	public void login() { 
		
		Client client = ClientBuilder.newClient().register(new Authenticator(user, password));


		
		
	}

	private String getUrl(String url)  {
		return url;
	}

	public Boolean Initialized() {
		return initialized;
	}

	public void finalize() {
	}
	
	public Boolean Islogin() {
		return islogin;
	}

	public String getToken() {
		return token;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
}
