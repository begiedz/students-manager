package org.studentsManager.src;

import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager{
    String url = "jdbc:mysql://localhost:3306/students-manager";
    String username = "root";
    String password = "";

    public StudentManagerImpl(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students");

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int age = resultSet.getInt(2);
                double grade = resultSet.getDouble(3);
                String studentID = resultSet.getString(4);

                System.out.println("Name: " + name + " " + "age: " + age+ " " + "grade: " + grade + " " + "studentID: "+ studentID);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void addStudent(Student student){};
    public void removeStudent(String studentID){};
    public void updateStudent(String studentID){};
    public ArrayList<Student> displayAllStudents(){
        ArrayList<Student> students = new ArrayList<>();
        return students;
    }
    public double calculateAverageGrade(){return 0;}
}
