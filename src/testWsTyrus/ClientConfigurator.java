package testWsTyrus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.ClientEndpointConfig.Configurator;

import com.luisma.connection.PrimaryAPI;

import javax.websocket.HandshakeResponse;

public class ClientConfigurator extends Configurator {

	public void beforeRequest(Map<String, List<String>> headers) {
		super.beforeRequest(headers);

		PrimaryAPI miApi = new PrimaryAPI();
		miApi.login();

		if (miApi.Islogin() ) {
			List<String> cookieList = headers.get("Cookie");
			if (null == cookieList) {
				cookieList = new ArrayList<>();
			}
			cookieList.add("JSESSIONID="+miApi.getJSESSIONID());    
			headers.put("Cookie", cookieList);
		}
		System.out.println(headers.toString());
	}

    public void afterResponse(HandshakeResponse hr) {
        //process the handshake response
    	System.out.println(hr.getHeaders().toString());
    }
}
