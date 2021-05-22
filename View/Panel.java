package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private JComboBox dropDownList;
	
	public Panel(String label, ActionListener parent) {
		this.setBackground(Color.white);
		this.setLayout(new GridLayout(2,1));
		
		JLabel enterLabel = new JLabel(label);
		this.add(enterLabel);
		
		//Initialise list
		String[] str = {""};
		this.dropDownList = new JComboBox(str);
		this.dropDownList.addActionListener(parent);
		this.add(dropDownList);
	}
		
	public JComboBox getList() {
		return dropDownList; 
	}
		
	public void updateList(ArrayList<String> str) {
		//need to convert array list into string array for JComboBox to work
		String[] courseString = new String[str.size()]; //make string array same size of array list
		for(int i=0;i<str.size();i++) {
			courseString[i]=str.get(i);
			this.dropDownList.addItem(courseString[i]); //now can add item 
		}		
	}
	public void deleteList() {
		this.dropDownList.removeAllItems();
		this.dropDownList.addItem(""); //adding first item in list to be empty
	}
	public void addItem(String str) {
		this.dropDownList.addItem(str);
	}
	public void removeItem(String str) {
		this.dropDownList.removeItem(str);
	}
	public void setSelection() {
		this.dropDownList.setSelectedIndex(0);
	}


}
