package testWsTyrus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.ClientEndpointConfig.Configurator;
import javax.websocket.HandshakeResponse;

import test.PrimaryAPI;

public class ClientConfigurator extends Configurator {

    public void beforeRequest(Map<String, List<String>> headers) {
        //affect the headers before request is sent
    	PrimaryAPI miApi = new PrimaryAPI();
    	miApi.login();

    	if (miApi.Islogin() ) {
        	List<String> lista = new ArrayList<String>();
        	lista.add(miApi.getToken());
    		headers.put("X-Auth-Token", lista);
    		lista = new ArrayList<String>();
    		lista.add(miApi.getJSESSIONID());
    		headers.put("JSESSIONID", lista);
/*    		lista = new ArrayList<String>();
    		lista.add(miApi.getPassword());
    		headers.put("x-password", lista);
*/    		lista = new ArrayList<String>();
    		lista.add("no-cache");
    		headers.put("cache-control", lista);
     	}
    	
    }

    public void afterResponse(HandshakeResponse hr) {
        //process the handshake response
    	System.out.println(hr.toString());
    }
}
