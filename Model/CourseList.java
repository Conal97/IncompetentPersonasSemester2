package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;

public class CourseList {

	private ArrayList<Course> LoC;

	public static void main(String[] args) {
		FileManager fileManager = FileManager.INSTANCE;
        // Read the file
        JSONArray array = fileManager.readFile();

        // Parse the teachers
        ArrayList<String> parsedCourses = fileManager.parseCourses(array);
        
        CourseList listOFcourses= new CourseList();
		listOFcourses.populateCourses(parsedCourses);

		// Access the array list that holds the teachers
        ArrayList<Course> courses = listOFcourses.getCourses();
        
        for (Course course : courses){
			
			System.out.println(course.getTrainingRequired());
			
		}
	}
	
	// Constructor
	public CourseList() {
		this.LoC = new ArrayList<Course>();
	}

	// Populat method -> convert course strings from parse courses method to course objects and add to LoC
	public void populateCourses(ArrayList<String> coursesFromFile) {

		for(String string: coursesFromFile) {
			String line = string;
			Scanner s = new Scanner(line);
			String name = s.next();
			int staffNum = Integer.parseInt(s.next());
			ArrayList<String> trainingReq = new ArrayList<String>();
			//while(s.hasNext())
			//	trainingReq.add(s.next());

			String[] req = s.next().split(",");
			for (int i = 0;i < req.length; i++) {
				trainingReq.add(req[i]);
			}

			Course c = new Course(name, staffNum, trainingReq);
			this.LoC.add(c);
			s.close();
		}
	}
	
	//returns the List of courses array
	public ArrayList<Course> getCourses(){
		return this.LoC;
	}

	//adds courses to the list of courses array
	public void addCourse(Course c) {
		this.LoC.add(c);
	}

	//subtracts courses to the list of courses array
	public void subCourse(Course c) {
		int pos = LoC.indexOf(c);
		this.LoC.remove(pos);
	}

	//returns the names of the courses, method from the List interface
	public String getNames() {
		String line = "";
		for(Course courseNames: LoC) {
			line += courseNames.getName() + "\n";
		}
		return line;
	}
	
	//toString method 
	public String toString() {
		String line = "";
		Iterator<Course> i = LoC.iterator();
		if(i.hasNext())
			line += i.next();
		while(i.hasNext())
			line += i.next();
		
		return line;
	}
}
