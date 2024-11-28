package org.studentsManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

//Główna klasa aplikacji, odpowiada za uruchomienie interfejsu użytkownika i główną pętlę programu
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view.fxml")));// Załadowanie pliku FXML
        primaryStage.setTitle("Students Manager v1.0 ~begiedz"); // Tytuł okna
        primaryStage.setScene(new Scene(root, 500, 400)); // Ustawienie wielkości okna
        primaryStage.show(); // Wyświetlenie okna
    }
    public static void main(String[] args) {
        System.out.println("Student Manager launched");
        launch(args); // Start JavaFX
    }
}
