package TestSwingTyrus;
//View.java
//(C) Joseph Mack 2011, jmack (at) wm7d (dot) net, released under GPL v3 (or any later version)

import java.awt.BorderLayout;

//inspired by Joseph Bergin's MVC gui at http://csis.pace.edu/~bergin/mvc/mvcgui.html

//View is an Observer

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.awt.event.WindowEvent;	//for CloseListener()
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class View implements java.util.Observer {

	//attributes as must be visible within class
	private TextField myTextField;
	private Button button; 
	private Button conect;
	private JTextField connTextField;

	//private Model model;		//Joe: Model is hardwired in, 
	//needed only if view initialises model (which we aren't doing)

	View() {
		System.out.println("View()");	

		//frame in constructor and not an attribute as doesn't need to be visible to whole class
		JFrame frame 		= new JFrame("simple MVC");
		frame.getContentPane().add("North", new Label("counter"));

		myTextField 		= new TextField();
		myTextField.setColumns(5);
		frame.getContentPane().add(myTextField, BorderLayout.WEST);

		connTextField = new JTextField();
		frame.getContentPane().add(connTextField, BorderLayout.EAST);
		connTextField.setColumns(10);

		//panel in constructor and not an attribute as doesn't need to be visible to whole class
		JPanel panel 		= new JPanel();
		button	 		= new Button("PressMe");
		panel.add(button);
		frame.getContentPane().add("South", panel);		
		
		conect = new Button("Connect");
		conect.setActionCommand("Connect");
		panel.add(conect);
		
		frame.addWindowListener(new CloseListener());	
		frame.setSize(200,100);
		frame.setLocation(100,100);
		frame.setVisible(true);

	} //View()

	// Called from the Model
	public void update(Observable obs, Object obj) {

		//who called us and what did they send?
		System.out.println ("View      : Observable is " + obs.getClass() + ", object passed is " + obj.getClass());

		//model Pull 
		//ignore obj and ask model for value, 
		//to do this, the view has to know about the model (which I decided I didn't want to do)
		//uncomment next line to do Model Pull
		//myTextField.setText("" + model.getValue());

		//model Push 
		//parse obj
		System.out.println ("Name object passed is " + obj.getClass().getName());
			
		
		myTextField.setText("" + ((Integer)obj).intValue());	//obj is an Object, need to cast to an Integer

	} //update()

	//to initialise TextField
	public void setValue(int v){
		myTextField.setText("" + v);
	} //setValue()

	public void addController(Controller controller){
		System.out.println("View      : adding controller");
		button.addActionListener(controller);	//need controller before adding it as a listener
		conect.addActionListener(controller);
	} //addController()

	//uncomment to allow controller to use view to initialise model	
	//public void addModel(Model m){
	//	System.out.println("View      : adding model");
	//	this.model = m;
	//} //addModel()

	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		} //windowClosing()
	} //CloseListener

} //View