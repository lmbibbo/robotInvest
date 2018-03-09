package testWsTyrus;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.luisma.model.Message;

public class SampleEncoder implements Encoder.Text<Message> {

	  @Override
	  public String encode(Message message) throws EncodeException {
	    return message.toString();
	  }

	  @Override
	  public void init(EndpointConfig ec) {
	    System.out.println("MessageEncoder - init method called");
	  }

	  @Override
	  public void destroy() {
	    System.out.println("MessageEncoder - destroy method called");
	  }

}
