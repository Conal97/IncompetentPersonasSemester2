import java.util.ArrayList;

public class Assigner {
    
    public Assigner(){}

    // Produce list of teachers that meet a selected courses requirements
    public ArrayList<String> teachersMeetRequirements(String courseName, TeacherList LoT, CourseList LoC){

        // Set output array list
        ArrayList<String> suitableTeachers = new ArrayList<String>();

        // Access the array list that holds the teachers
        ArrayList<Teacher> teachers = LoT.getTeachers();

        // Training required array to compare course and teacher
        ArrayList<String> trainingRequired = new ArrayList<String>();
        trainingRequired = matchCourse(courseName, LoC).getTrainingRequired();

        for (Teacher teacher : teachers) {
            
            ArrayList<String> teacherTraining = teacher.getTrainingAttended();
            // Check if each teacher has the ALL required training
            if(teacherTraining.containsAll(trainingRequired)) {
                // Append teacher name to suitable teacher array
                suitableTeachers.add(teacher.getName());
            } 
        }
        return suitableTeachers;
    }

    // Produce a list of teachers that have not been assigned to the selected course
    public ArrayList<String> unassignedTeachers(String courseName, TeacherList LoT, CourseList LoC) {
        
        // Set output array list
        ArrayList<String> unassingedTeachers = new ArrayList<String>();

        // Access the array list that holds the teachers
        ArrayList<Teacher> teachers = LoT.getTeachers();

        // Teachers assigned to course array to compare course and teacher
        ArrayList<Teacher> teachersOnCourse = new ArrayList<Teacher>();
        teachersOnCourse = matchCourse(courseName, LoC).getTeachers();

        for (Teacher teacher : teachers) {
            
            if(!teachersOnCourse.contains(teacher)) {
                unassingedTeachers.add(teacher.getName());
            }
        }
        return unassingedTeachers;
    }
    
    // Still need to test assign to course and assign training

    // Assign teachers to course
    public ArrayList<String> assignTeacherToCourse(String courseName, String teacherName, TeacherList LoT, CourseList LoC) {
        
        // Set output **** Could just output string straight away???? ****
        ArrayList<String> assignedTeachers = new ArrayList<String>();

        // Access selected course and teacher
        Course course = matchCourse(courseName, LoC);
        Teacher teacher = matchTeacher(teacherName, LoT);

        // Append teacher to the course
        course.addTeacher(teacher);

        // Append teacher to output and return
        assignedTeachers.add(course.toString());
        return assignedTeachers;

        // Don't know if outputs work!!!
    }

    // Assign teachers training
    public ArrayList<String> assingTeacherTraining(String courseName, String teacherName, TeacherList LoT, CourseList LoC) {

        // Set output **** Could just output string straight away???? ****
        ArrayList<String> assignedTraining = new ArrayList<String>();

        // Access selected course and teacher
        Course course = matchCourse(courseName, LoC);
        Teacher teacher = matchTeacher(teacherName, LoT);

        // Training required for the course
        ArrayList<String> requiredTraining = course.getTrainingRequired();

        // Determine what training the teacher can take
        for (String string : requiredTraining) {

            // Check teacher doesn't already have the training and hasn't already been assigned the training
            if (!teacher.getTrainingAttended().contains(string) && !teacher.getTrainingToAttend().contains(teacher)) {
                teacher.addCourseToAttend(string);
                assignedTraining.add(teacher.toString());
            }
        }
        return assignedTraining;
        // Don't know if outputs work!!!
    }

    // Match teacher helper method
    public Teacher matchTeacher(String teacherName, TeacherList LoT) {

        // Access the array list that holds the teachers
        ArrayList<Teacher> teachers = LoT.getTeachers();

        // Match teacher with selected teacher
        for (Teacher teacher : teachers) {
            
            if(teacher.getName().equals(teacherName)) {
                // Return teacher object
                return teacher;
            }
        }
        return null;
    }


    // Match course helper method
    public Course matchCourse(String courseName, CourseList LoC) {

        // Access the array list that holds the courses
        ArrayList<Course> courses = LoC.getCourses();

        // Match course object with selected course
        for (Course course : courses) {
        
            if (course.getName().equals(courseName)) {
                // Return course object
                return course;
            }
        }
        return null; 
    }
}
