package Tests;

import java.util.ArrayList;
import org.json.JSONArray;

import Model.*;

public class Tester {
    // Main to test
    public static void main(String[] args) {
        
        // Create instance of filehandler object
        FileManager fileManager = FileManager.INSTANCE;

        // Write the file
        fileManager.fileWrite();

        // Read the file
        JSONArray array = fileManager.readFile();

        // Parse the teachers
        ArrayList<String> parsedTeachers = fileManager.parseTeachers(array);

        // Testing expected output -> "Alice English,Maths"
        //                         -> "Bob English, Science"
        //                         -> "Charlie Maths,Science"
        for (String string : parsedTeachers) System.out.println(string);

        // Parse the courses
        ArrayList<String> parsedCourses = fileManager.parseCourses(array);

        // Testing expected output -> "English 2 English"
        //                         -> "Science 2 Maths,Science "
        for (String courseString : parsedCourses) System.out.println(courseString); 

        // Hard coding a teacher who's been assigned training to test teacher update method
        TeacherList teachers = new TeacherList();
        teachers.populateTeachers(parsedTeachers);
        ArrayList<Teacher> eachTeacher = teachers.getTeachers();
        for (Teacher teacher : eachTeacher) {
            System.out.println(teacher.getName());
            System.out.println(teacher.getTrainingAttended());
        }
        Teacher testTeacher = eachTeacher.get(0); // teacher is Alice
        testTeacher.addTrainingToAttend("ScienceTraining");
        testTeacher.addTrainingToAttend("GeographyTraining");
        Teacher testTeacher2 = eachTeacher.get(1);
        testTeacher2.addTrainingToAttend("GeographyTraining");

        // Testing updateTeachers -> want that 'Science' & 'Geography' have been added to the 'TrainingToAttend' array of Alice
        //fileHandler.updateTeacherTrainingToAttend(teachers, array);

        // Hard coding a course who's been assigned a teacher
        CourseList courses = new CourseList();
        courses.populateCourses(parsedCourses);
        ArrayList<Course> eachCourse = courses.getCourses();
        for (Course course : eachCourse) {
            System.out.println(course.getName());
            System.out.println(course.getTrainingRequired());
        }
        Course testCourse = eachCourse.get(0); // Test course is English
        testCourse.addTeacher(testTeacher);

        // Testing updateCourse -> want that 'Alice' teacher object has been added as a teacher to the test course
        //fileHandler.updateCourseTrainingAssinged(courses, array);

        // ------------------------------------------------------ //
        // Testing the assigner class

        // Instantiate class
        Assigner assigner = new Assigner();

        // Testing the teacherMeetRequirments method
        ArrayList<String> suitableTeachers = assigner.teachersMeetRequirements("English", teachers, courses);
        System.out.println("Suitable teachers are: " + suitableTeachers);

        // Testing the unassinged teachers method
        ArrayList<String> unassingedTeachers = assigner.unassignedTeachers("English", teachers, courses);
        System.out.println("Unassinged teachers are: " + unassingedTeachers);

        // Testing assign teacher to course & updating JSON file
        assigner.assignTeacherToCourse("Science", "Charlie", teachers, courses);
        fileManager.updateCourseTeacherAssinged(courses, array);

        // Testing assign training to teacher & updating JSON file
        assigner.assignTeacherTraining("English", "Charlie", teachers, courses);
        fileManager.updateTeacherTrainingToAttend(teachers, array);
    }
}
