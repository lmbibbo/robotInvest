package testWsTyrus;

@ServerEndpoint(value = "/echo")
public class EchoEndpointAnnotated {
    @OnMessage
    public String onMessage(String message, Session session) {
    	
        return message;
    }
}