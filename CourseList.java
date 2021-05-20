
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CourseList {

	private ArrayList<Course> LoC;
	
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
			while(s.hasNext())
				trainingReq.add(s.next());

			Course c = new Course(name, staffNum, trainingReq);
			LoC.add(c);
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
