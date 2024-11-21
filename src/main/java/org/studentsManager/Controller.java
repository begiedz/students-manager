package org.studentsManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import org.studentsManager.src.Student;
import org.studentsManager.src.StudentManagerImpl;

import java.util.List;

public class Controller {

    private final StudentManagerImpl studentManager = new StudentManagerImpl();

    @FXML private Text text;
//    @FXML private Button button;

    @FXML private void handleDisplayAllStudents(){
        List<Student> students = studentManager.displayAllStudents();

        if (students.isEmpty()) {
            text.setText("No students in database");
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

        text.setText(allStudentsText.toString());
    }

//    @FXML private void handleButtonClick() {
//     text.setText(toggleText ? Main.student1.getName(): "Nothing to show");
//     toggleText = !toggleText;
//     Main.student1.displayInfo();
//    }
}
