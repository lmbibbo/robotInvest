package com.luisma.view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class CheckboxList {

	JList<CheckboxListItem> list;
	DefaultListModel<CheckboxListItem> model;	

	public CheckboxList() {

		model = new DefaultListModel<CheckboxListItem>();
		list = new JList<CheckboxListItem>(model);
		
		list.setCellRenderer(new CheckboxListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (list.isEnabled()) {
					@SuppressWarnings("unchecked")
					JList<CheckboxListItem> list =
							(JList<CheckboxListItem>) event.getSource();

					// Get index of item clicked
					int index = list.locationToIndex(event.getPoint());
					CheckboxListItem item = (CheckboxListItem) list.getModel()
							.getElementAt(index);

					// Toggle selected state
					item.setSelected(!item.isSelected());

					// Repaint cell
					list.repaint(list.getCellBounds(index, index));
				}
			}
		});
	}

	public DefaultListModel<CheckboxListItem> getModel() {
		return model;
	}

	public JList<CheckboxListItem> getList() {
		return list;
	}

	
}