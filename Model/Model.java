package Model;

import java.util.ArrayList;

public class Model{ 
	private TeacherList teacherList;
	private CourseList courseList;
	public FileManager fileManager = FileManager.INSTANCE; 
	public Assigner assigner = new Assigner(); 
    public Model() {
    	this.teacherList = new TeacherList();
    	this.courseList = new CourseList();
    }
           
    public TeacherList getTeachers() {
		return teacherList;
	}
	public CourseList getCourses() {
		return courseList;
	}

	public String arrayListToString(ArrayList<String> str) {
		String string = " "; //make string array same size of array list
		for(int i=0;i<str.size();i++) { //loop through array and add to string array
			string+=str.get(i) + "\n";
		}		
		return string;
	}
}
	

	
		

