package org.studentsManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
private boolean toggleText = true;
    @FXML private Text text;
    @FXML private Button button;
    @FXML private void handleButtonClick() {
     text.setText(toggleText ? Main.student1.getName(): "Nothing to show");
     toggleText = !toggleText;
     Main.student1.displayInfo();
    }
}
