package testWsTyrus;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class SampleEncoder implements Encoder.Text<Message> {

	  @Override
	  public String encode(Message message) throws EncodeException {

	    JsonObject jsonObject = Json.createObjectBuilder()
	        .add("type", message.getType())
	        .add("level", message.getLevel())
	        .add("entries", Json.createArrayBuilder())
	        .build();
	    return jsonObject.toString();

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
