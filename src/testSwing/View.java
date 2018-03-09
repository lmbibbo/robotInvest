package testSwing;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View {

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
	
	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		} //windowClosing()
	} //CloseListener

	public TextField getMyTextField() {
		return myTextField;
	}

	public Button getButton() {
		return button;
	}

	public Button getConect() {
		return conect;
	}

	public JTextField getConnTextField() {
		return connTextField;
	}
}
