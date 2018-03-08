package com.luisma.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import testSwing.View.CloseListener;
import javax.swing.JSplitPane;

public class MiView {
	
	MiView() {
		System.out.println("View()");	

		//frame in constructor and not an attribute as doesn't need to be visible to whole class
		JFrame frame 		= new JFrame("Robotito.... en acción");

		frame.addWindowListener(new CloseListener());	
		frame.setSize(600,400);
		frame.setLocation(10,10);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.WEST);
		frame.setVisible(true);

	} //View()
	
	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		} //windowClosing()
	} //CloseListener
}
