package View;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Model.*;
import Controller.Controller;

public class View extends JFrame{
	private JButton courseDirectorButton;
	private JButton assignButton;
	private JButton assignTrainingButton;
	private JButton saveExit;
	private Panel panel1;
	private Panel panel2;
	private JTextArea text1;
	private JTextArea text2;
	private JLabel label1;
	private JLabel label2;
	private Model modelObject;
	private Controller controllerObject;
	
	public View(Model model, Controller controller) {
		this.controllerObject = controller;
		this.modelObject = model;
		
		//initialise JFrame with size and title etc
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    this.setSize(600,400);
	    this.setLocation(100,100);
	    this.setTitle("PTT System");
	    this.setLayout(new FlowLayout());	

	    //initialise panel
	    panel1 = new Panel("Pick a Course", controllerObject);
	    panel2 = new Panel("Pick a Teacher", controllerObject);

	    //initialise labels
	    this.label1 = new JLabel("Teachers Assigned to Course");
	    label1.setBorder(BorderFactory. createEmptyBorder( 100 , 1 , 1 , 100 )); //create border to position label over text area
	    this.label2 = new JLabel("Training Assigned to Teacher");
	    label2.setBorder(BorderFactory. createEmptyBorder( 100 , 1 , 1 , 10 ));

	    //initialise buttons
	    this.courseDirectorButton = new JButton("Course Director");
		this.courseDirectorButton.addActionListener(controllerObject);  //assign action listener to button
	    this.assignButton = new JButton("Assign Teacher");
		this.assignButton.addActionListener(controllerObject);
		this.assignTrainingButton = new JButton("Assign Training");
		this.assignTrainingButton.addActionListener(controllerObject);
		this.saveExit = new JButton("Save & Exit");
		this.saveExit.addActionListener(controllerObject);

		//initialise text area
		this.text1 = new JTextArea(10,25); //create text area with predetermined dimensions
		this.text1.setEditable(false); //ensure text area can't be written in
		this.text2 = new JTextArea(10,25);
		this.text2.setEditable(false);

		//add panels and buttons to JFrame
		this.add(courseDirectorButton);
		this.add(panel1);
		this.add(panel2);
	    this.add(assignButton);
	    this.add(assignTrainingButton);
	    this.add(saveExit);
	    this.add(label1);
	    this.add(label2);
	    this.add(text1);
	    this.add(text2);
		
	    //disable buttons
	    this.assignButton.setEnabled(false);
	    this.assignTrainingButton.setEnabled(false);
	}

	public JButton getCourseDirectorButton() {
		return courseDirectorButton;
	}

	public JButton getAssignButton() {
		return assignButton;
	}

	public JButton getAssignTrainingButton() {
		return assignTrainingButton;
	}

	public JButton getSaveExit() {
		return saveExit;
	}

	public Panel getPanel1() {
		return panel1;
	}

	public Panel getPanel2() {
		return panel2;
	}

	public JTextArea getText1() {
		return text1;
	}

	public JTextArea getText2() {
		return text2;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public Model getModelObject() {
		return modelObject;
	}

	public Controller getControllerObject() {
		return controllerObject;
	}
}
