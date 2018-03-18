package testSwing;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.luisma.view.CheckboxList;
import com.luisma.view.CheckboxListItem;
import com.luisma.view.CheckboxListRenderer;

public class View {

	//attributes as must be visible within class
	private TextField myTextField;
	private Button button; 
	private Button conect;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private CheckboxList mylist;

	final   String[] listData = {"Appleeee", "Orange", "Cherry", "Blue Berry", "Banana", "Red Plum", "Watermelon"};
	//private Model model;		//Joe: Model is hardwired in, 
	//needed only if view initialises model (which we aren't doing)

	View() {
		System.out.println("View()");	

		//frame in constructor and not an attribute as doesn't need to be visible to whole class
		JFrame frame 		= new JFrame("simple MVC");
		frame.getContentPane().add("North", new Label("counter"));

		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BorderLayout(0, 0));

		mylist = new CheckboxList();
	
		int s = listData.length;
		for(int i=0; i<s; i++){
			mylist.getModel().addElement(new CheckboxListItem(listData[i]));
		}

		panel_2.add(mylist.getList(), BorderLayout.CENTER);
		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		myTextField 		= new TextField();
		panel_3.add(myTextField, BorderLayout.CENTER);
		myTextField.setColumns(5);

		//panel in constructor and not an attribute as doesn't need to be visible to whole class
		JPanel panel 		= new JPanel();
		button	 		= new Button("PressMe");
		panel.add(button);
		frame.getContentPane().add("South", panel);		

		conect = new Button("Connect");
		conect.setActionCommand("Connect");
		panel.add(conect);

		frame.addWindowListener(new CloseListener());	
		frame.setSize(500,400);
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

	public CheckboxList getMylist() {
		return mylist;
	}

}
