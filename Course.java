import java.util.ArrayList;

public class Course {
	private String name;
	private int numStaffRequired;
	private ArrayList <String> trainingRequired;
	private ArrayList <Teacher> teachers = new ArrayList <Teacher>();

	public Course(String n, int num, ArrayList <String> tr) {
		this.name = n;
		this.numStaffRequired = num;
		this.trainingRequired = tr;
	}
	
	public String getName() {
		return this.name; 
	}
	public ArrayList <String> getTrainingRequired(){
		return this.trainingRequired;
	}
	public ArrayList <Teacher> getTeachers(){
		return this.teachers;
	}
	public void addTeacher(Teacher t){
		this.teachers.add(t);
	}
	public boolean fullyStaffed() {
		 //if course has enough teachers allocated
		if(this.numStaffRequired == this.teachers.size()) {
			return true;
		}
		return false;
	}
   
	public String toString() {
		String str = this.name + ": ";
		for(int i=0;i<teachers.size();i++) {
			str += teachers.get(i).getName() + " ";
		}
		return str; 
	}
}
