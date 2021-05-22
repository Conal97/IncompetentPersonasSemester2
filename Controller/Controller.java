package Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.json.JSONArray;

import Model.*;
import View.*;

public class Controller implements ActionListener{

	// Attributes
	private Model modelObject;
	private View viewObject; 
	private String teacherName;
	private String courseName;
	private boolean teacherAssignment; 
	
	// Constructor
	public Controller(Model model) {

		// Get reference to the model
		modelObject = model;

		// Initialise view
		viewObject = new View(model, this);
		viewObject.setVisible(true);
	}

	// Event handling
	public void actionPerformed(ActionEvent e) {

		// If course director button is pressed
		if (e.getSource()==viewObject.getCourseDirectorButton()) {

			// Get reference to file manager from model 
			FileManager fmanager = this.modelObject.fileManager;

			// Get reference to model teacherlist and courselist
			TeacherList LoT = this.modelObject.getTeachers();
			CourseList LoC = this.modelObject.getCourses();

			//file handler stuff
	        fmanager.fileWrite(); //write JSON file

			// Read the file
			JSONArray array = fmanager.readFile();

			// Parse teachers and courses
	        ArrayList<String> parsedTeachers = fmanager.parseTeachers(array); 
	        ArrayList<String> parsedCourses = fmanager.parseCourses(array);  
	        
	        //update model with a list of teacher and course objects from JSON file output
	        LoT.populateTeachers(parsedTeachers);
	        LoC.populateCourses(parsedCourses);

			// Convert to iterable arraylist
			ArrayList<Course> courses = LoC.getCourses();

			// Iterate over each course
			for (Course course : courses) {

				// If course does not have enough staff, then do not show in list
				if(!course.fullyStaffed()) {

					// Add courses that need staff to the list
					viewObject.getPanel1().addItem(course.getName());
				}
			}
			
			// When courseDirector button is pressed disable button so can't be pressed again
			viewObject.getCourseDirectorButton().setEnabled(false);			  	  
		}
		// If course drop down list is selected
		else if (e.getSource()==viewObject.getPanel1().getList()) { 

			teacherAssignment = false;

			// Get reference to the assinger, teacherList and courseList
			Assigner controllerAssigner = modelObject.assigner;
			TeacherList LoT = modelObject.getTeachers();
			CourseList LoC = modelObject.getCourses();

			// Find what teachers can teach each course & retrieve the clicked course
			JComboBox cb = (JComboBox)e.getSource();
			this.courseName = (String)cb.getSelectedItem();	
			
			// Empty list to add new list of teachers
			viewObject.getPanel2().deleteList(); 

			// Assinger method -> find what teachers have requirements to teach a course and update list
			viewObject.getPanel2().updateList(controllerAssigner.teachersMeetRequirements(this.courseName, LoT, LoC)); 

			// If teacher list is empty -> there are no more teachers that fit requirements
			if(viewObject.getPanel2().getList().getItemCount()==1) { 

				// Set true since teachers now need to be assigned training
				this.teacherAssignment = true;  
				
				// Assinger method -> find what teachers need training
				viewObject.getPanel2().updateList(controllerAssigner.unassignedTeachers(this.courseName, LoT, LoC));
			}

			// Disable buttons
			viewObject.getAssignButton().setEnabled(false); 	
			viewObject.getAssignTrainingButton().setEnabled(false);
			
		}
		
		// If teacher drop down list is selected
		else if(e.getSource()==viewObject.getPanel2().getList()) { 

			// If a teacher needs to be allocated training then set assign button invisible
			if(teacherAssignment) { 
				viewObject.getAssignButton().setEnabled(false); 
				viewObject.getAssignTrainingButton().setEnabled(true); 
			} else {
				viewObject.getAssignButton().setEnabled(true); 
				viewObject.getAssignTrainingButton().setEnabled(false); 
			}
			// Retrieve the item that was clicked
			JComboBox cb = (JComboBox)e.getSource();
			teacherName = (String)cb.getSelectedItem();
			}

			// If assign teacher to course button is pressed
			else if (e.getSource()==viewObject.getAssignButton()) {

				int button = 0;

				// Get reference to the assinger, teacherList and courseList
				Assigner controllerAssigner = modelObject.assigner;
				TeacherList LoT = modelObject.getTeachers();
				CourseList LoC = modelObject.getCourses();

				// Assign teachers training and update text field
				viewObject.getText1().setText(modelObject.arrayListToString(controllerAssigner.assignTeacherToCourse(courseName, teacherName, LoT, LoC)));

				// Remove teacher from list and set selection to empty element (for display)
				viewObject.getPanel2().removeItem(this.teacherName); 
				viewObject.getPanel2().setSelection();

				// If course has been allocated sufficient teachers, remove course from list
				viewObject.getPanel1().deleteList();
				
				// Accessing model course list as iterable
				//CourseList LoC = this.modelObject.getCourses();
				ArrayList<Course> courses = LoC.getCourses();

				for (Course course : courses) {
					
					// If course does not have enough staff, then do not display in drop down list
					if (!course.fullyStaffed()) {
						viewObject.getPanel1().addItem(course.getName());
					}
			}
		} 
		
		// If assign training button is pressed
		else if (e.getSource()==viewObject.getAssignTrainingButton()) {

			int button = 1;

			// Get reference to the assinger, teacherList and courseList
			Assigner controllerAssigner = modelObject.assigner;
			TeacherList LoT = modelObject.getTeachers();
			CourseList LoC = modelObject.getCourses();

			// Set text2 to the teachers that need to undergo training
			viewObject.getText2().setText(modelObject.arrayListToString(controllerAssigner.assignTeacherTraining(courseName, teacherName, LoT, LoC)));

			// Remove teacher from list and set selection to empty element
			viewObject.getPanel2().removeItem(teacherName); 
			viewObject.getPanel2().setSelection();
			
		}
		
		// If the save and exit button is pressed
		else if(e.getSource()==viewObject.getSaveExit()) {

			// Get reference to file manager from model 
			FileManager fmanager = modelObject.fileManager;

			// Access JSON array from file
			JSONArray array = fmanager.readFile();

			// Update JSON file with updated teachersList and coursesList objects from model
			fmanager.updateTeacherTrainingToAttend(this.modelObject.getTeachers(), array);
			fmanager.updateCourseTeacherAssinged(this.modelObject.getCourses(), array);

			// End 
			System.exit(0);
		}
	}	
}
