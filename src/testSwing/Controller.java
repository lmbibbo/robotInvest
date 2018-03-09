package testSwing;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model m, View v) {
		addModel(m);
		addView(v);
		initModel(1);
		initView();
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
		view.getMyTextField().setText(String.valueOf(model.getCounter()));
	}

	public void initController() {
		view.getButton().addActionListener(e -> incrementValue());
		view.getConect().addActionListener(e -> decrementValue());
	}

	private Object decrementValue() {
		model.decrementValue();
		view.getMyTextField().setText(String.valueOf(model.getCounter()));
		return null;
	}

	private Object incrementValue() {
		model.incrementValue();
		view.getMyTextField().setText(String.valueOf(model.getCounter()));
		return null;
	}
}
