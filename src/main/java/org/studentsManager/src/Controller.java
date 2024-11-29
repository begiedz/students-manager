package org.studentsManager.src;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    @FXML private void handleAddStudent() {
        String studentId = studentIdField.getText();
        String name = studentNameField.getText();
        String ageText = studentAgeField.getText();
        String gradeText = studentGradeField.getText();

        // Sprawdzenie, czy wszystkie pola są wypełnione
        if (studentId.isEmpty() || name.isEmpty() || ageText.isEmpty() || gradeText.isEmpty()) {
            output.setText("Error: All fields must be filled out.");
            return;
        }

        try {
            // Parsowanie danych wejściowych
            int age = Integer.parseInt(ageText);
            double grade = Double.parseDouble(gradeText);

            // Utworzenie nowego studenta i dodanie go do bazy
            Student newStudent = new Student(name, age, grade, studentId);
            studentManager.addStudent(newStudent);

            output.setText("Student added succesfully: " + newStudent.getName() + " " + newStudent.getStudentID());
        } catch (NumberFormatException e) {
            output.setText("Error: Age must be an number and grade must be a number or decimal.");
        }
    }

    // Usuwa studenta na podstawie podanego ID.
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
    // Aktualizuje dane istniejącego studenta na podstawie wprowadzonych danych.
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
            // Sprawdzenie, które pola są wypełnione, aby tylko te dane aktualizować
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
    // Oblicza średnią ocen wszystkich studentów i wyświetla ją w polu tekstowym
    @FXML private void handleCalculateAverage() throws Exception {
       Double average = studentManager.calculateAverageGrade();
       if (average == 0){
           output.setText("No average grade to show.");
       }
        output.setText("Average grade for all students: " + average);

    }
    // Wyświetla dane wszystkich studentów w bazie
    @FXML private void handleDisplayAllStudents(){
        List<Student> students = studentManager.displayAllStudents();

        try{
            if (students.isEmpty()) {
                output.setText("No students in database");
                return;
            }

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
        }catch(Exception e){
            output.setText(e.getMessage());
        }
    }
}
