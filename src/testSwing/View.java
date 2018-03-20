package testSwing;

import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class View {

//	JScrollPane scrollPane;
	private JFrame frame;
	private JPanel panel;
	
	//attributes as must be visible within class
	private Button button; 
	private Button conect;
	
	private TopPanel topPane;

	View() {
		System.out.println("View()");	

		//frame in constructor and not an attribute as doesn't need to be visible to whole class
		frame 		= new JFrame("simple MVC");

		topPane = new TopPanel();
		JSplitPane top = topPane.getSplitPane();
		top.setBorder(null);

		//panel in constructor and not an attribute as doesn't need to be visible to whole class
		panel 		= new JPanel();
		button	 		= new Button("PressMe");
		panel.add(button);
		
		conect = new Button("Connect");
		conect.setActionCommand("Connect");
		panel.add(conect);
		
        //Create a split pane and put "top" (a split pane)
        //and JLabel instance in it.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                              top, panel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(300);
 
        //Provide minimum sizes for the two components in the split pane
//        top.setMinimumSize(new Dimension(200, 150));
//        panel.setMinimumSize(new Dimension(30, 30));
 				
		frame.setContentPane(splitPane);	
        frame.pack();
        frame.setVisible(true);

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


	public Button getButton() {
		return button;
	}

	public Button getConect() {
		return conect;
	}

	public TopPanel getTopPane() {
		return topPane;
	}

}
