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
            // Tworzenie obiektu studenta z danych z pól tekstowych
            Student student = new Student(
                    studentNameField.getText(),
                    Integer.parseInt(studentAgeField.getText()),
                    Double.parseDouble(studentGradeField.getText()),
                    studentIdField.getText()
            );

            studentManager.addStudent(student);
            output.setText("Student added successfully.");

        } catch (InvalidStudentDataException e) {
            output.setText("Validation error: " + e.getMessage());
        } catch (NumberFormatException e) {
            output.setText("Error: Age must be an integer and grade must be a valid number.");
        } catch (Exception e) {
            // Obsługa nieoczekiwanych błędów
            output.setText("Unexpected error: " + e.getMessage());
        }
    }


    // Usuwa studenta na podstawie podanego ID.
    @FXML
    private void handleRemoveStudent() {
        String studentId = studentIdField.getText();
        String name = studentNameField.getText();
        // Walidacja pustego pola na ID
        if (studentId.isEmpty()) {
            output.setText("Error: Student ID must be provided to remove a student.");
            return;
        }

        try {
            studentManager.removeStudent(studentId);
            output.setText("Student " + name + " with ID: " + studentId + " removed successfully.");
        } catch (IllegalArgumentException e) {
            output.setText("Validation error: " + e.getMessage());
        } catch (Exception e) {
            output.setText("Unexpected error: " + e.getMessage());
        }
    }

    // Aktualizuje dane istniejącego studenta na podstawie wprowadzonych danych.
    @FXML
    private void handleUpdateStudent(){
        String studentId = studentIdField.getText();
        String name = studentNameField.getText();
        String ageText = studentAgeField.getText();
        String gradeText = studentGradeField.getText();

        if (studentId.isEmpty()){
            output.setText("Student ID not provided to update specific student");
            return;
        }
        try {
            //Parsowanie danych wejściowych
            Integer age = ageText.isEmpty() ? null : Integer.parseInt(ageText);
            Double grade = gradeText.isEmpty() ? null : Double.parseDouble(gradeText);

            Student student = new Student(name.isEmpty() ? null : name, age, grade, studentId);

            studentManager.updateStudent(student);
            output.setText("Student with ID: " + studentId + " updated successfully.");
        } catch (NumberFormatException e) {
            output.setText("Error: Age must be an integer and grade must be a number.");
        } catch (IllegalArgumentException e) {
            output.setText("Validation error: " + e.getMessage());
        } catch (Exception e) {
            output.setText("Unexpected error: " + e.getMessage());
        }
    }

    // Oblicza średnią ocen wszystkich studentów i wyświetla ją w polu tekstowym
    @FXML
    private void handleCalculateAverage() throws Exception {
        try {
            double average = studentManager.calculateAverageGrade();
            if (average == 0) {
                output.setText("No students in the database to calculate an average.");
            } else {
                output.setText("Average grade for all students: " + average);
            }
        } catch (Exception e) {
            output.setText("Unexpected error: " + e.getMessage());
        }
    }
    // Wyświetla dane wszystkich studentów w bazie
    @FXML
    private void handleDisplayAllStudents(){
        try {
            List<Student> students = studentManager.displayAllStudents();
            if (students.isEmpty()) {
                output.setText("No students in the database.");
            } else {
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
            }
        } catch (Exception e){
            output.setText(e.getMessage());
        }
    }
}
