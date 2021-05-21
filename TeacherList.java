
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;

public class TeacherList {
	
	private ArrayList<Teacher> LoT;

	public static void main(String[] args) {
		FileHandler fileHandler = new FileHandler();
        // Read the file
        JSONArray array = fileHandler.readFile();

        // Parse the teachers
        ArrayList<String> parsedTeachers = fileHandler.parseTeachers(array);
        
        TeacherList listOFteachers = new TeacherList();
		listOFteachers.populateTeachers(parsedTeachers);

		// Access the array list that holds the teachers
        ArrayList<Teacher> teachers = listOFteachers.getTeachers();
        
        for (Teacher teacher : teachers){
			
			System.out.println(teacher.getTrainingAttended());
		}
	}
	
	// Constructor 
	public TeacherList() {
		this.LoT = new ArrayList<Teacher>();
	}

	// Populate method -> convert teacher strings from parse teachers to teacher objects and add to LoT
	public void populateTeachers(ArrayList<String> teachersFromFile) {

		for (String input :teachersFromFile) {
        	String line = input;
        	Scanner s = new Scanner(line);
        	ArrayList<String> trainingAttended = new ArrayList<String>();
        	String name = s.next();

			String[] training = s.next().split(",");
			for (int i = 0; i < training.length; i++) {
				trainingAttended.add(training[i]);
			}

			Teacher t = new Teacher(name,trainingAttended);
			this.LoT.add(t);
			s.close();
		}
	}
	
	//returns the list of teachers
	public ArrayList<Teacher> getTeachers() {
		return this.LoT;
	}
	
	//add teachers to the list of teachers
	public void addTeacher(Teacher t) {
		this.LoT.add(t);
	}
	
	//subtract teachers from the list of teachers
	public void subTeacher(Teacher t) {
		int pos = LoT.indexOf(t);
		this.LoT.remove(pos);
	}
	
	//returns the names of the teachers in the List, method from the List Interface
	public String getNames() {
		String line = "";
		for(Teacher teacher: LoT) {
			line += teacher.getName() + "\n";
		}
		return line;
	}
	
	//toString method
	public String toString() {
		String line = "";
		Iterator<Teacher> i = LoT.iterator();
		if(i.hasNext())
			line += i.next();
		while(i.hasNext())
			line += "\n" + i.next();
		
		return line;
	}
}
