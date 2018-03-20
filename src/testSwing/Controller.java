package testSwing;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisma.connection.PrimaryAPI;
import com.luisma.model.Answer;
import com.luisma.model.AnswerInstruments;
import com.luisma.view.CheckboxListItem;

public class Controller {
	private Model model;
	private View view;
	private PrimaryAPI miApi;
	

	public Controller(Model m, View v) {
		addModel(m);
		addView(v);
		initModel(1);
		initView();
		miApi = new PrimaryAPI();
	}

	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.model = m;
	} //addModel()

	public void addView(View v){
		System.out.println("Controller: adding view");
		this.view = v;
	} //addView()

	public void initModel(int x){
		model.setCounter(x);
	} //initModel()
	
	private void initView() {
		// TODO Auto-generated method stub
		view.getTopPane().getMyTextField().setText(String.valueOf(model.getCounter()));
	}

	public void initController() {
		view.getButton().addActionListener(e -> incrementValue());
		view.getConect().addActionListener(e -> connect());
	}

	private Object chequed() {
		
		
		System.out.println("Controller: CheckBox chequed: ");	
		return null;
	}

	private Object decrementValue() {
		model.decrementValue();
		view.getTopPane().getMyTextField().setText(String.valueOf(model.getCounter()));
		view.getTopPane().getMylist().getList().setEnabled(true);
		return null;
	}
	
	private void connect() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		miApi.login();
		try {
			AnswerInstruments resp = objectMapper.readValue(miApi.getInstrumentos(), AnswerInstruments.class);
			System.out.println(System.currentTimeMillis()+" <- "+objectMapper.writeValueAsString(resp));
			
			int s = resp.getInstruments().size();
			for(int i=0; i<s; i++){
				if (resp.getInstruments().get(i).getInstrumentId().getMarketId().equals("ROFX")) {
					view.getTopPane().getMylist().getModel().addElement(new CheckboxListItem(resp.getInstruments().get(i).getInstrumentId().getSymbol()));					
				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		view.getConect().setEnabled(false);
	}

	private Object incrementValue() {
		model.incrementValue();
		view.getTopPane().getMyTextField().setText(String.valueOf(model.getCounter()));
		view.getTopPane().repaint();
//		view.getMylist().getList().setEnabled(false);
		return null;
	}
}
