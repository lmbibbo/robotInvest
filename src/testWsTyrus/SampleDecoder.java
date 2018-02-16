package testWsTyrus;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class SampleDecoder implements Decoder.Text<Message> {

	  @Override
	  public Message decode(String jsonMessage) throws DecodeException {

	    Message message = new Message();

	    return message;
	  }

	  @Override
	  public boolean willDecode(String jsonMessage) {
	      return true;
	  }

	  @Override
	  public void init(EndpointConfig ec) {
	    System.out.println("MessageDecoder -init method called");
	  }

	  @Override
	  public void destroy() {
	    System.out.println("MessageDecoder - destroy method called");
	  }

}
