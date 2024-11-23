package org.studentsManager.src;

import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager{
        private final DatabaseConnection dbConnection = new DatabaseConnection();

    public void addStudent(Student student){
        String query = "INSERT INTO students (name, age, grade, studentID) VALUES (?,?,?,?)";
    }
    public void removeStudent(String studentID){
            String query = "DELETE FROM students WHERE studentID = ?";
    }
    public void updateStudent(String studentID){
            String query = "UPDATE students SET name = ?, age = ? grade = ?, studentID = ? WHERE studentID = ?";
    }

    public ArrayList<Student> displayAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double grade = resultSet.getDouble("grade");
                String studentID = resultSet.getString("studentID");

                Student student = new Student(name,age,grade,studentID);
                students.add(student);
            }
        } catch (SQLException e){
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    public double calculateAverageGrade(){return 0;}
}
