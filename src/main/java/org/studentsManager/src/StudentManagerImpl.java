package org.studentsManager.src;

import org.studentsManager.src.exceptions.InvalidStudentDataException;
import org.studentsManager.src.exceptions.StudentNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Obiekt odpowiedzialny za zarządzanie połączeniem z bazą danych i logikę aplikacji
public class StudentManagerImpl implements StudentManager{

    // Walidacja danych
    private void validateStudent(Student student) {
        if (student == null || student.getName() == null || student.getName().isEmpty()) {
            throw new InvalidStudentDataException("Student name cannot be null or empty.");
        }
        if (student.getAge() <= 0) {
            throw new InvalidStudentDataException("Student age must be greater than zero.");
        }
        if (student.getGrade() < 0 || student.getGrade() > 100) {
            throw new InvalidStudentDataException("Grade must be between 0 and 100.");
        }
        if (student.getStudentID() == null || student.getStudentID().isEmpty()) {
            throw new InvalidStudentDataException("Student ID cannot be null or empty.");
        }
    }

    // Dodaje nowego studenta do bazy danych.
    // param student - obiekt reprezentujący studenta, który ma zostać dodany.
    public void addStudent(Student student){
        validateStudent(student);
        String query = "INSERT INTO students (name, age, grade, studentID) VALUES (?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Przypisanie wartości do parametrów zapytania
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setDouble(3, student.getGrade());
            preparedStatement.setString(4, student.getStudentID());

            // Wykonanie zapytania
            preparedStatement.executeUpdate();
            System.out.println("addStudent query executed");
        } catch (SQLException e){
            System.err.println("Error while adding student: " + e.getMessage());
        }
    }

    // Usuwa studenta z bazy danych na podstawie jego identyfikatora
    public void removeStudent(String studentID){
        if (studentID == null || studentID.isEmpty()) {
            throw new InvalidStudentDataException("Student ID cannot be null or empty.");
        }

            String query = "DELETE FROM students WHERE studentID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Ustawienie wartości id w zapytaniu
            preparedStatement.setString(1, studentID);

            preparedStatement.executeUpdate();
            System.out.println("removeStudent query executed");
        } catch (SQLException e){
            System.err.println("Error while removing student: " + e.getMessage());
        }
    }

    // Aktualizuje dane istniejącego studenta w bazie danych
    public void updateStudent(Student student) {
        if (student.getStudentID() == null || student.getStudentID().isEmpty()) {
            throw new InvalidStudentDataException("Student ID is required for update.");
        }

        // Budowa zapytania SQL dynamicznie na podstawie dostarczonych danych
        StringBuilder queryBuilder = new StringBuilder("UPDATE students SET ");
        List<Object> parameters = new ArrayList<>();

        // Dodawanie warunków tylko dla nie-nullowych wartości
        if (student.getName() != null) {
            queryBuilder.append("name = ?, ");
            parameters.add(student.getName());
        }

        if (student.getAge() != null) {
            queryBuilder.append("age = ?, ");
            parameters.add(student.getAge());
        }
        if (student.getGrade() != null) {
            queryBuilder.append("grade = ?, ");
            parameters.add(student.getGrade());
        }

        if (parameters.isEmpty()) {
            throw new InvalidStudentDataException("No fields provided to update.");
        }

        // Usunięcie ostatniego przecinka i dodanie WHERE
        queryBuilder.deleteCharAt(queryBuilder.length() - 2);
        queryBuilder.append("WHERE studentID = ?");
        parameters.add(student.getStudentID());

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {

            // Ustawianie parametrów w zapytaniu
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new StudentNotFoundException("No student found with ID: " + student.getStudentID());
            }
            System.out.println("Student " + student.getStudentID() + " has been updated.");

        } catch (SQLException e) {
            System.err.println("Error while updating student: " + e.getMessage());
        }
    }

    // Pobiera listę wszystkich studentów z bazy danych
    public ArrayList<Student> displayAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iteracja po wynikach zapytania i tworzenie obiektów Student
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double grade = resultSet.getDouble("grade");
                String studentID = resultSet.getString("studentID");

                Student student = new Student(name,age,grade,studentID);
                // Dodanie studenta do ArrayListy
                students.add(student);
            }
        } catch (SQLException e){
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    public double calculateAverageGrade() {
        List<Student> students = displayAllStudents();
        double totalAmount = 0.0;

        if (students.isEmpty()){
            return 0;
        }
        // Sumowanie ocen wszystkich studentów
        for (Student student : students) {
            totalAmount += student.getGrade();
        }

        // Zwracanie obliczonej średniej
        return totalAmount / students.size();
    }
}
