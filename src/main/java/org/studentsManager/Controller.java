package org.studentsManager;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import org.studentsManager.src.Student;
import org.studentsManager.src.StudentManagerImpl;

import java.util.List;

public class Controller {

    private final StudentManagerImpl studentManager = new StudentManagerImpl();

    @FXML private TextArea output;

    @FXML private void handleDisplayAllStudents(){
        List<Student> students = studentManager.displayAllStudents();

        try{
            if (students.isEmpty()) {
                output.setText("No students in database");
                return;
            }

            StringBuilder allStudentsText = new StringBuilder();
            for (Student student : students) {
                allStudentsText.append("Name: ")
                        .append(student.getName())
                        .append(", Age: ")
                        .append(student.getAge())
                        .append(", Grade: ")
                        .append(student.getGrade())
                        .append(", ID: ")
                        .append(student.getStudentID())
                        .append("\n");
            }

            output.setText(allStudentsText.toString());
        }catch(Exception e){
            output.setText(e.getMessage());
        }

    }
}
