package org.studentsManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button myButton;

    @FXML
    private void initialize() {
        myButton.setOnAction(event -> System.out.println("Button clicked!"));
    }
}
