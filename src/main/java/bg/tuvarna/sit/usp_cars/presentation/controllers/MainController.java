package bg.tuvarna.sit.usp_cars.presentation.controllers;

import bg.tuvarna.sit.usp_cars.business.services.*;
import bg.tuvarna.sit.usp_cars.data.entities.Car;
import bg.tuvarna.sit.usp_cars.data.entities.Mechanic;
import bg.tuvarna.sit.usp_cars.data.entities.Owner;
import bg.tuvarna.sit.usp_cars.data.entities.Service;
import bg.tuvarna.sit.usp_cars.presentation.models.*;
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
import java.time.LocalDate;
import java.util.*;

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
    private TextField tfMileage;
    @FXML
    private TextField tfMechanicName;
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
    private ComboBox<CarModel> tfCarChoice;
    @FXML
    private ComboBox<ServiceModel> tfServiceChoice;
    @FXML
    private ComboBox<MechanicModel> tfMechanicChoice;
    @FXML
    private TextField tfPriceService; // da se dobavi v fxml

    public MainController(Stage stage){
        s=stage;
    }

    public void onAddOwnerButtonClick(ActionEvent actionEvent){
        //code on Add owner button click
        OwnerModel ownerModel=new OwnerModel(tfOwnerName.getText(),Integer.parseInt(tfCarsOwned.getText()));
        OwnerService ownerService=OwnerService.getInstance();
        if(ownerService.addOwner(ownerModel)){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Owner added!",ButtonType.OK);
            alert.show();
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING,"Owner not added!",ButtonType.OK);
            alert.show();
        }
    }

    public void onAddPaymentButtonClick(ActionEvent actionEvent){
        //code on Add payment method button click
        PaymentModel paymentModel=new PaymentModel(tfPaymentMethod.getText());
        PaymentService paymentService=PaymentService.getInstance();
        if(paymentService.addPayment(paymentModel)){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Payment added!",ButtonType.OK);
            alert.show();
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING,"Payment not added!",ButtonType.OK);
            alert.show();
        }
    }

    public void onAddMechanicButtonClick(ActionEvent actionEvent){
        //code on Add car method button click
        MechanicModel mechanicModel=new MechanicModel(tfMechanicName.getText());
        MechanicService mechanicService=MechanicService.getInstance();
        if(mechanicService.addMechanic(mechanicModel))
        {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Mechanic added!",ButtonType.OK);
            alert.show();
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING,"Mechanic not added!",ButtonType.OK);
            alert.show();
        }
    }

    public void onAddServiceButtonClick(ActionEvent actionEvent){
        //code on Add service method button click
        ServiceModel serviceModel=new ServiceModel(tfServiceName.getText(),tfServiceType.getText());
        ServiceService service=ServiceService.getInstance();
        if(service.addService(serviceModel)){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Service added!",ButtonType.OK);
            alert.show();
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING,"Service not added!",ButtonType.OK);
            alert.show();
        }
    }

    public void onAddCarButtonClick(ActionEvent actionEvent){
        //code on Add car method button click
        OwnerService ownerService=OwnerService.getInstance();
        PaymentService paymentService=PaymentService.getInstance();
        CarService carService=CarService.getInstance();
        LocalDate ld = tfDateOfReg.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
        Date date = c.getTime();
        CarModel carModel=new CarModel(tfManufacturer.getText(),tfModel.getText(),tfEngine.getText(),
                tfTransmission.getText(),tfDriveType.getText(),tfVin.getText(),
                Double.parseDouble(tfPrice.getText()),date,
                Integer.parseInt(tfMileage.getText()),tfVehicleType.getText(),
                Double.parseDouble(tfDiscount.getText())
                ,ownerService.findOwner(tfOwnerCombo.getValue()),
                paymentService.findPaymentType(tfPaymentCombo.getValue()));
        if(carService.addCar(carModel)){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Car added!",ButtonType.OK);
            alert.show();
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING,"Car not added!",ButtonType.OK);
            alert.show();
        }

    }

    public void onAddCarServiceButtonClick(ActionEvent actionEvent){
        //code on Add carService method button click
        CarService carService=CarService.getInstance();
        MechanicService mechanicService=MechanicService.getInstance();
        ServiceService serviceService=ServiceService.getInstance();
        CarServiceService carServiceService=CarServiceService.getInstance();
        Car car=carService.findCar(tfCarChoice.getValue()); //null
        Service service= serviceService.findService(tfServiceChoice.getValue());
        Mechanic mechanic= mechanicService.findMechanic(tfMechanicChoice.getValue());
        CarServiceModel carServiceModel=new CarServiceModel(car,service,mechanic,Double.parseDouble(tfPriceService.getText()));
        if(carServiceService.addCarService(carServiceModel)){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"CarService added!",ButtonType.OK);
            alert.show();
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING,"CarService not added!",ButtonType.OK);
            alert.show();
        }


    }


    @FXML
    public void switchAddPane(ActionEvent actionEvent){
        switch (comboBoxChoices.getValue()){
            case "Car":
                ObservableList<OwnerModel> owners=OwnerService.getInstance().getAllOwners();
                tfOwnerCombo.setItems(owners);
                ObservableList<PaymentModel> payments=PaymentService.getInstance().getAllPayments();
                tfPaymentCombo.setItems(payments);
                switchRightPane(addCarPane, addPane);
                break;
            case "CarService":
                ObservableList<CarModel> cars=CarService.getInstance().getAllCars();
                tfCarChoice.setItems(cars);
                ObservableList<ServiceModel> services=ServiceService.getInstance().getAllServices();
                tfServiceChoice.setItems(services);
                ObservableList<MechanicModel> mechanics=MechanicService.getInstance().getAllMechanics();
                tfMechanicChoice.setItems(mechanics);
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
