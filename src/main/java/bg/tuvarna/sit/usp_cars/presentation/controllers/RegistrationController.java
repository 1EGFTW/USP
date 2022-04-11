package bg.tuvarna.sit.usp_cars.presentation.controllers;

import bg.tuvarna.sit.usp_cars.business.services.UserService;
import bg.tuvarna.sit.usp_cars.data.entities.User;
import bg.tuvarna.sit.usp_cars.presentation.models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bg.tuvarna.sit.usp_cars.common.Constants.View.LOGIN_VIEW;
import static bg.tuvarna.sit.usp_cars.common.Constants.View.MAIN_VIEW;

public class RegistrationController {

    public Stage s;
    @FXML
    public TextField user_username;
    @FXML
    public PasswordField user_password;
    @FXML
    public Button login;
    @FXML
    public Button emptyButton;
    @FXML
    public CheckBox is_admin;
    @FXML
    public TextField verification_code;
    private final UserService userService= UserService.getInstance();

    public RegistrationController(Stage stage){
        s=stage;
    }


    @FXML
    public void onRegistrationButtonClick(ActionEvent actionEvent) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(user_username.getText());
        boolean matchFound = matcher.find();
        Matcher matcher1 = pattern.matcher(user_password.getText());
        boolean matchFound1 = matcher1.find();
        if(matchFound && user_username.getLength()>=6) {
            if(matchFound1 && user_password.getLength()>=6){
                UserModel userToReg = new UserModel(user_username.getText(),user_password.getText());
                if(!userService.registerNewUser(userToReg))
                {
                    infoAlert("User already exists!");
                    user_username.setText("");
                    user_password.setText("");
                }
                else{
                    infoAlert("User registered in successfully!");
                    loadNewPage(LOGIN_VIEW);
                }

            }else{
                infoAlert("Password must contain at least 1 number and be longer than 6 characters");
                user_username.setText("");
                user_password.setText("");
            }

        } else {
            infoAlert("Username must contain at least 1 number and be longer than 6 characters");
            user_username.setText("");
            user_password.setText("");
        }
    }

    @FXML
    public void onAlreadyRegisteredButtonClick(ActionEvent actionEvent){
        loadNewPage(LOGIN_VIEW);
    }

    public void loadNewPage(String path){
        try {
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            Stage stage = new Stage();
            fxmlLoader.setController(new LoginController(stage));
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
        /*DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("Alerts.css");
        dialogPane.getStyleClass().add("Alert");*/
        alert.show();
    }










}
