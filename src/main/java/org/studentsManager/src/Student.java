package org.studentsManager.src;

public class Student {
    private String name;
    private Integer age;
    private Double grade;
    private String studentID;

    // Konsturktor
    public Student(String name, Integer age, Double grade, String studentID ){
        this.name = name;
        this. age = age;
        this.grade = grade;
        this.studentID = studentID;
    }
    // Gettery i settery odpowiadające za dostęp do danych
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getGrade() { return grade; }
    public void setGrade(Double grade) { this.grade = grade; }

    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public record StudentInfo(String name, Integer age, Double grade, String studentID) {}

    public StudentInfo displayInfo() {
        return new StudentInfo(name, age, grade,studentID);
    }
}
