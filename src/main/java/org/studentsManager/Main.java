package org.studentsManager;

import org.studentsManager.src.Student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view.fxml")));
        primaryStage.setTitle("Students Manager");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
    static Student student1 = new Student("Dariusz",26,0,"123");
    public static void main(String[] args) {
        System.out.println("Student Manager launched");
        launch(args);
    }
}
