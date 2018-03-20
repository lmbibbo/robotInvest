package testSwing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.luisma.view.CheckboxList;
import com.luisma.view.CheckboxListItem;

public class TopPanel extends JPanel {
	
	private CheckboxList mylist;
	private JSplitPane splitPane;
	private TextField myTextField;
	
	public TopPanel() {

		myTextField = new TextField();
		mylist = new CheckboxList();
		JScrollPane listScrollPane = new JScrollPane(mylist.getList());
		
		JPanel pictureScrollPane = new JPanel();
		
		pictureScrollPane.add(myTextField, BorderLayout.CENTER);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				listScrollPane, pictureScrollPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		Dimension minimumSize = new Dimension(100, 50);
		listScrollPane.setMinimumSize(minimumSize);
		pictureScrollPane.setMinimumSize(minimumSize);


	}

	public CheckboxList getMylist() {
		return mylist;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public TextField getMyTextField() {
		return myTextField;
	}

	
	 private static void createAndShowGUI() {

	      //Create and set up the window.
	      JFrame frame = new JFrame("SplitPaneDemo");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      TopPanel splitPaneDemo = new TopPanel();
	      
	      
	      String[] imageNames = { "Bird", "Cat", "Dog", "Rabbit", "Pig", "dukeWaveRed",
	    	      "kathyCosmo", "lainesTongue", "left", "middle", "right", "stickerface"};
	      
	      int s = imageNames.length;
	      for(int i=0; i<s; i++){
	    	 
	    	  splitPaneDemo.getMylist().getModel().addElement(new CheckboxListItem(imageNames[i]));					

	      }
			
	      splitPaneDemo.getMyTextField().setText("Hola");
	      
	      frame.getContentPane().add(splitPaneDemo.getSplitPane());
	      
	      

	      //Display the window.
	      frame.pack();
	      frame.setVisible(true);
	  }

	  public static void main(String[] args) {
	      //Schedule a job for the event-dispatching thread:
	      //creating and showing this application's GUI.
	      javax.swing.SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	              createAndShowGUI();
	          }
	      });
	  }
	
	

}
