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

import static bg.tuvarna.sit.usp_cars.common.Constants.View.LOGIN_VIEW;

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
    private final UserService userService= UserService.getInstance();

    public RegistrationController(Stage stage){
        s=stage;
    }


    @FXML
    public void onRegistrationButtonClick(ActionEvent actionEvent) {
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
