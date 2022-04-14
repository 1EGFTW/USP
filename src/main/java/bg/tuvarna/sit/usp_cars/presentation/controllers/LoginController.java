package bg.tuvarna.sit.usp_cars.presentation.controllers;

import bg.tuvarna.sit.usp_cars.business.services.UserService;
import bg.tuvarna.sit.usp_cars.data.entities.User;
import bg.tuvarna.sit.usp_cars.presentation.models.UserModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bg.tuvarna.sit.usp_cars.common.Constants.View.*;

public class LoginController {

    public Stage s;
    @FXML
    public TextField user_username;
    @FXML
    public PasswordField user_password;
    @FXML
    public Button login;
    private final UserService userService= UserService.getInstance();

    public LoginController(Stage stage){
        s=stage;
    }


    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) {
        UserModel userToLog = new UserModel(user_username.getText(),user_password.getText());
        if(userService.logIn(userToLog))
        {
            infoAlert("User successfully logged in!");
            try {
                s.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MAIN_VIEW));
                Stage stage = new Stage();
                fxmlLoader.setController(new MainController(stage));
                Parent root1 = (Parent) fxmlLoader.load();
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.setWidth(1024);
                stage.setHeight(768);
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        else{
            infoAlert("Error logging in");
            user_username.setText("");
            user_password.setText("");
        }
    }

    @FXML
    public void onNoRegistrationButtonClick(ActionEvent actionEvent){
        loadNewPage(REGISTRATION_VIEW);
    }

    public void loadNewPage(String path){
        try {
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            Stage stage = new Stage();
            fxmlLoader.setController(new RegistrationController(stage));
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setWidth(1024);
            stage.setHeight(768);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void infoAlert(String info){
        Alert alert=new Alert(Alert.AlertType.INFORMATION,info,ButtonType.OK);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("Alerts.css");
        dialogPane.getStyleClass().add("Alert");
        alert.show();
    }










}
