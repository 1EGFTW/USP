package bg.tuvarna.sit.usp_cars.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;
    private Stage s;

    public HelloController(Stage stage){
        s=stage;
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}