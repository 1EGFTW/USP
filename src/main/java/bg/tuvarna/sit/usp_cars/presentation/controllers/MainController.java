package bg.tuvarna.sit.usp_cars.presentation.controllers;

import bg.tuvarna.sit.usp_cars.data.entities.Car;
import bg.tuvarna.sit.usp_cars.data.entities.Mechanic;
import bg.tuvarna.sit.usp_cars.data.entities.Service;
import bg.tuvarna.sit.usp_cars.presentation.models.OwnerModel;
import bg.tuvarna.sit.usp_cars.presentation.models.PaymentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static bg.tuvarna.sit.usp_cars.common.Constants.View.LOGIN_VIEW;

public class MainController implements Initializable {
    public Stage s;

    @FXML
    private Pane wrapperPane;
    @FXML
    private Pane addPane;
    @FXML
    private Pane logoPane;
    @FXML
    private Pane addOwnerPane;
    @FXML
    private Pane addPaymentPane;
    @FXML
    private Pane addServicePane;
    @FXML
    private Pane addMechanicPane;
    @FXML
    private ScrollPane addCarPane;
    @FXML
    private Pane addCarServicePane;

    @FXML
    private ComboBox<String> comboBoxChoices;
    @FXML
    private TextField tfOwnerName;
    @FXML
    private TextField tfCarsOwned;
    @FXML
    private TextField tfServiceName;
    @FXML
    private TextField tfServiceType;
    @FXML
    private TextField tfPaymentMethod;
    @FXML
    private TextField tfManufacturer;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfEngine;
    @FXML
    private TextField tfTransmission;
    @FXML
    private TextField tfDriveType;
    @FXML
    private TextField tfVin;
    @FXML
    private TextField tfPrice;
    @FXML
    private DatePicker tfDateOfReg;
    @FXML
    private TextField tfVehicleType;
    @FXML
    private TextField tfDiscount;
    @FXML
    private ComboBox<OwnerModel> tfOwnerCombo;
    @FXML
    private ComboBox<PaymentModel> tfPaymentCombo;
    @FXML
    private ComboBox<Car> tfCarChoice;
    @FXML
    private ComboBox<Service> tfServiceChoice;
    @FXML
    private ComboBox<Mechanic> tfMechanicChoice;

    public MainController(Stage stage){
        s=stage;
    }

    public void onAddOwnerButtonClick(ActionEvent actionEvent){
        //code on Add owner button click
    }

    public void onAddPaymentButtonClick(ActionEvent actionEvent){
        //code on Add payment method button click
    }

    public void onAddMechanicButtonClick(ActionEvent actionEvent){
        //code on Add car method button click
    }

    public void onAddServiceButtonClick(ActionEvent actionEvent){
        //code on Add service method button click
    }

    public void onAddCarButtonClick(ActionEvent actionEvent){
        //code on Add car method button click
    }

    public void onAddCarServiceButtonClick(ActionEvent actionEvent){
        //code on Add carService method button click
    }


    @FXML
    public void switchAddPane(ActionEvent actionEvent){
        switch (comboBoxChoices.getValue()){
            case "Car":
                /*ObservableList<OwnerModel> owners=OwnerService.getInstance().getAllOwners();
                tfOwnerCombo.setItems(owners);
                ObservableList<PaymentModel> payments=PaymentService.getInstance().getAllPayments();
                tfPaymentCombo.setItems(payments);*/
                switchRightPane(addCarPane, addPane);
                break;
            case "CarService":
                switchRightPane(addCarServicePane, addPane);
                break;
            case "Mechanic":
                switchRightPane(addMechanicPane, addPane);
                break;
            case "Owner":
                switchRightPane(addOwnerPane, addPane);
                break;
            case "Payment method":
                switchRightPane(addPaymentPane, addPane);
                break;
            case "Service":
                switchRightPane(addServicePane, addPane);
                break;

        }
    }

    @FXML
    public void switchToAdd(ActionEvent actionEvent){
        switchDoublePane(addPane);
    }

    public void switchDoublePane(Node paneName){
        wrapperPane.getChildren().clear();
        wrapperPane.getChildren().add(paneName);
        wrapperPane.getChildren().add(logoPane);
    }

    public void switchRightPane(Node paneName, Node frontPane){
        wrapperPane.getChildren().clear();
        wrapperPane.getChildren().add(frontPane);
        wrapperPane.getChildren().add(paneName);
    }

    public void goBack(ActionEvent actionEvent){
        loadNewPage(LOGIN_VIEW);
    }

    private void loadNewPage(String path){
        try {
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            Stage stage = new Stage();
            fxmlLoader.setController(new LoginController(stage));
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillComboBox();
    }

    private void fillComboBox() {
        List<String> choices = new ArrayList<>();
        choices.add("Car");
        choices.add("CarService");
        choices.add("Mechanic");
        choices.add("Owner");
        choices.add("Payment method");
        choices.add("Service");
        comboBoxChoices.setItems(FXCollections.observableList(choices));
    }

    public void infoAlert(String info){
        Alert alert=new Alert(Alert.AlertType.INFORMATION,info, ButtonType.OK);
        /*DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("Alerts.css");
        dialogPane.getStyleClass().add("Alert");*/
        alert.show();
    }
}
