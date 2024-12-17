package org.studentsManager.src;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.studentsManager.src.exceptions.InvalidStudentDataException;

import java.sql.SQLException;
import java.util.List;

// Klasa kontrolera obsługująca interakcję z użytkownikiem.
public class Controller {
    private final StudentManagerImpl studentManager = new StudentManagerImpl();

    // Komponenty GUI
    @FXML private TextArea output;
    @FXML private TextField studentIdField;
    @FXML private TextField studentNameField;
    @FXML private TextField studentAgeField;
    @FXML private TextField studentGradeField;

    // Dodaje nowego studenta na podstawie danych wprowadzonych przez użytkownika.
    @FXML
    private void handleAddStudent() {
        try {
            studentManager.addStudent(new Student(
                    studentNameField.getText(),
                    Integer.parseInt(studentAgeField.getText()),
                    Double.parseDouble(studentGradeField.getText()),
                    studentIdField.getText()
            ));
            output.setText("Student added successfully.");
        } catch (Exception e) {
            output.setText("Error: " + e.getMessage());
        }
    }

    // Usuwa studenta na podstawie podanego ID.
    @FXML
    private void handleRemoveStudent() {
        try {
            studentManager.removeStudent(studentIdField.getText());
            output.setText("Student removed successfully.");
        } catch (Exception e) {
            output.setText("Error: " + e.getMessage());
        }
    }

    // Aktualizuje dane istniejącego studenta na podstawie wprowadzonych danych.
    @FXML
    private void handleUpdateStudent(){
        try {
            studentManager.updateStudent(new Student(
                            studentNameField.getText(),
                            Integer.parseInt(studentAgeField.getText()),
                            Double.parseDouble(studentGradeField.getText()),
                            studentIdField.getText()
                    )
            );
            output.setText("Student updated successfully.");
        } catch (Exception e) {
            output.setText("Error: " + e.getMessage());
        }
    }

    // Oblicza średnią ocen wszystkich studentów i wyświetla ją w polu tekstowym
    @FXML
    private void handleCalculateAverage() throws Exception {
        try {
            double average = studentManager.calculateAverageGrade();
            output.setText("Average grade for all students: " + average);
        } catch (Exception e) {
            output.setText("Error: " + e.getMessage());
        }
    }
    // Wyświetla dane wszystkich studentów w bazie
    @FXML
    private void handleDisplayAllStudents(){
        try {
            List<Student> students = studentManager.displayAllStudents();
            // Tworzenie tekstu zawierającego informacje o wszystkich studentach
            StringBuilder allStudentsText = new StringBuilder();
            for (Student student : students) {
                allStudentsText
                        .append("Name: ")
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
        } catch (Exception e){
            output.setText("Error: " + e.getMessage());
        }
    }
}
