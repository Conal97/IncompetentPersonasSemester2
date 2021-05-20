
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TeacherList {
	
	private ArrayList<Teacher> LoT;
	
	// Constructor 
	public TeacherList() {
		this.LoT = new ArrayList<Teacher>();
	}

	// Populate method -> convert teacher strings from parse teachers to teacher objects and add to LoT
	public void populateTeachers(ArrayList<String> teachersFromFile) {

		for(String input :teachersFromFile) {
        	String line = input;
        	Scanner s = new Scanner(line);
        	ArrayList<String> training = new ArrayList<String>();
        	String name = s.next();

        	while(s.hasNext())
        		training.add(s.next());

				Teacher t = new Teacher(name,training);
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
