package org.studentsManager.src;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class Controller {

    private final StudentManagerImpl studentManager = new StudentManagerImpl();

    @FXML private TextArea output;
    @FXML private TextField studentIdField;
    @FXML private TextField studentNameField;
    @FXML private TextField studentAgeField;
    @FXML private TextField studentGradeField;

    @FXML private void handleAddStudent() {
        String studentId = studentIdField.getText();
        String name = studentNameField.getText();
        String ageText = studentAgeField.getText();
        String gradeText = studentGradeField.getText();

        if (studentId.isEmpty() || name.isEmpty() || ageText.isEmpty() || gradeText.isEmpty()) {
            output.setText("Error: All fields must be filled out.");
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            double grade = Double.parseDouble(gradeText);

            Student newStudent = new Student(name, age, grade, studentId);
            studentManager.addStudent(newStudent);

            output.setText("Student added succesfully: " + newStudent.getName() + " " + newStudent.getStudentID());
        } catch (NumberFormatException e) {
            output.setText("Error: Age must be an number and grade must be a number or decimal.");
        }
    }
    @FXML private void handleRemoveStudent(){
        String studentId = studentIdField.getText();

        if (studentId.isEmpty()){
            output.setText("Student ID not provided to remove specific student");
            return;
        }

        try{
            studentManager.removeStudent(studentId);
            output.setText("Student with ID: " + studentId + " removed succesfully.");
        }catch(Exception e){
            output.setText(e.getMessage());
        }
    }
    @FXML private void handleUpdateStudent(){
        String studentId = studentIdField.getText();
        String name = studentNameField.getText();
        String ageText = studentAgeField.getText();
        String gradeText = studentGradeField.getText();

        Integer age = null;
        Double grade = null;

        if (studentId.isEmpty()){
            output.setText("Student ID not provided to update specific student");
            return;
        }
        try{
            if(name.isEmpty()){
                name = null;
            }
            if (!ageText.isEmpty()) {
                age = Integer.parseInt(ageText);
            }
            if (!gradeText.isEmpty()) {
                grade = Double.parseDouble(gradeText);
            }

            Student newStudent = new Student(name, age, grade, studentId);

            studentManager.updateStudent(newStudent);
            output.setText("Student with ID: " + studentId + " updated succesfully.");
        }catch(Exception e){
            output.setText(e.getMessage());
        }
    }
    @FXML private void handleCalculateAverage(){
        List<Student> students = studentManager.displayAllStudents();
        double averageGrade = 0.0;
        double totalAmount = 0.0;
try{
        for (Student student : students){
            totalAmount += student.getGrade();
        }

        averageGrade = totalAmount / students.size();
        output.setText("Average grade of all students: " + averageGrade);
}catch(Exception e){System.err.println(e.getMessage());}
    }
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
