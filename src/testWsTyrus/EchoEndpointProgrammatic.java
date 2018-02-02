package testWsTyrus;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.xml.transform.Source;
import javax.xml.ws.Binding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.EndpointReference;

import org.w3c.dom.Element;

public class EchoEndpointProgrammatic extends Endpoint {
 
    public void onOpen(final Session session, EndpointConfig config) {
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

	@Override
	public Binding getBinding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EndpointReference getEndpointReference(Element... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends EndpointReference> T getEndpointReference(Class<T> arg0, Element... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Executor getExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getImplementor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Source> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPublished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void publish(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publish(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setExecutor(Executor arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMetadata(List<Source> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperties(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
}
