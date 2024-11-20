package org.studentsManager.src;

import java.util.ArrayList;

public interface StudentManager {
    void addStudent(Student student);
    void removeStudent(String studentID);
    void updateStudent(String studentID);
    ArrayList<Student> displayAllStudents();
    double calculateAverageGrade();
}
