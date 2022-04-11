package bg.tuvarna.sit.usp_cars.presentation.controllers;

import bg.tuvarna.sit.usp_cars.business.services.*;
import bg.tuvarna.sit.usp_cars.business.services.CarService;
import bg.tuvarna.sit.usp_cars.data.entities.*;
import bg.tuvarna.sit.usp_cars.presentation.models.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private Pane checkPane;
    @FXML
    private Pane checkMechanicPane;
    @FXML
    private Pane checkOwnerPane;
    @FXML
    private Pane checkPaymentPane;
    @FXML
    private Pane checkServicePane;
    @FXML
    private Pane checkCarPane;
    @FXML
    private Pane checkCarServicePane;

    @FXML
    private ComboBox<String> comboBoxChoices;
    @FXML
    private ComboBox<String> comboBoxCheckChoices;
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
    private TextField tfPriceService;
    @FXML
    private TableView<MechanicModel> mechanicTable = new TableView<>();
    @FXML
    private TableColumn<MechanicModel, String> mechanicName = new TableColumn<>();
    @FXML
    private TableView<OwnerModel> ownerTable = new TableView<>();
    @FXML
    private TableColumn<OwnerModel, String> ownerName = new TableColumn<>();
    @FXML
    private TableColumn<OwnerModel, Integer> ownerCarsOwned = new TableColumn<>();
    @FXML
    private TableView<PaymentModel> paymentTable = new TableView<>();
    @FXML
    private TableColumn<OwnerModel, String> paymentType = new TableColumn<>();
    @FXML
    private TableView<ServiceModel> serviceTable = new TableView<>();
    @FXML
    private TableColumn<ServiceModel, String> serviceName = new TableColumn<>();
    @FXML
    private TableColumn<ServiceModel, String> serviceType = new TableColumn<>();
    @FXML
    private TableView<CarModel> carTable = new TableView<>();
    @FXML
    private TableColumn<CarModel, String> carManufacturer = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, String> carModel = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, String> carEngine = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, String> carTransmission = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, String> carDriveType = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, String> carVin = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, Double> carPrice = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, Date> carDate = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, Integer> carMileage = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, String> carType = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, Double> carDiscount = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, String> carOwner = new TableColumn<>();
    @FXML
    private TableColumn<CarModel, String> carPayment = new TableColumn<>();
    @FXML
    private TableView<CarServiceModel> carServiceTable = new TableView<>();
    @FXML
    private TableColumn<CarServiceModel, String> tbCarServiceCar = new TableColumn<>();
    @FXML
    private TableColumn<CarServiceModel, String> tbCarServiceService = new TableColumn<>();
    @FXML
    private TableColumn<CarServiceModel, String> tbCarServiceMechanic = new TableColumn<>();
    @FXML
    private TableColumn<CarServiceModel, Double> tbCarServicePrice = new TableColumn<>();

    public MainController(Stage stage){
        s=stage;
    }

    public void onAddOwnerButtonClick(ActionEvent actionEvent){
        //code on Add owner button click
        OwnerModel ownerModel=new OwnerModel(tfOwnerName.getText(),Integer.parseInt(tfCarsOwned.getText()));
        OwnerService ownerService=OwnerService.getInstance();
        if(ownerService.addOwner(ownerModel)){
            showAlert(Alert.AlertType.CONFIRMATION,"Owner added!");
        }else {
            showAlert(Alert.AlertType.WARNING,"Owner not added!");
        }
    }

    public void onAddPaymentButtonClick(ActionEvent actionEvent){
        //code on Add payment method button click
        PaymentModel paymentModel=new PaymentModel(tfPaymentMethod.getText());
        PaymentService paymentService=PaymentService.getInstance();
        if(paymentService.addPayment(paymentModel)){
            showAlert(Alert.AlertType.CONFIRMATION,"Payment added!");
        }else {
            showAlert(Alert.AlertType.WARNING,"Payment not added!");
        }
    }

    public void onAddMechanicButtonClick(ActionEvent actionEvent){
        //code on Add car method button click
        MechanicModel mechanicModel=new MechanicModel(tfMechanicName.getText());
        MechanicService mechanicService=MechanicService.getInstance();
        if(mechanicService.addMechanic(mechanicModel))
        {
            showAlert(Alert.AlertType.CONFIRMATION,"Mechanic added!");
        }else {
            showAlert(Alert.AlertType.WARNING,"Mechanic not added!");
        }
    }

    public void onAddServiceButtonClick(ActionEvent actionEvent){
        //code on Add service method button click
        ServiceModel serviceModel=new ServiceModel(tfServiceName.getText(),tfServiceType.getText());
        ServiceService service=ServiceService.getInstance();
        if(service.addService(serviceModel)){
            showAlert(Alert.AlertType.CONFIRMATION,"Service added!");
        }else {
            showAlert(Alert.AlertType.WARNING,"Service not added!");
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
            showAlert(Alert.AlertType.CONFIRMATION,"Car added!");
        }else {
            showAlert(Alert.AlertType.WARNING,"Car not added!");
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
            showAlert(Alert.AlertType.CONFIRMATION,"CarService added!");
        }else {
            showAlert(Alert.AlertType.WARNING,"CarService not added!");
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
                switchDoublePane(addCarPane, addPane);
                break;
            case "CarService":
                ObservableList<CarModel> cars=CarService.getInstance().getAllCars();
                tfCarChoice.setItems(cars);
                ObservableList<ServiceModel> services=ServiceService.getInstance().getAllServices();
                tfServiceChoice.setItems(services);
                ObservableList<MechanicModel> mechanics=MechanicService.getInstance().getAllMechanics();
                tfMechanicChoice.setItems(mechanics);
                switchDoublePane(addCarServicePane, addPane);
                break;
            case "Mechanic":
                switchDoublePane(addMechanicPane, addPane);
                break;
            case "Owner":
                switchDoublePane(addOwnerPane, addPane);
                break;
            case "Payment method":
                switchDoublePane(addPaymentPane, addPane);
                break;
            case "Service":
                switchDoublePane(addServicePane, addPane);
                break;
            case "null": showAlert(Alert.AlertType.WARNING, "Please choose an option from the combo box!"); //not working

        }
    }

    public void switchCheckPane(ActionEvent actionEvent){
        switch (comboBoxCheckChoices.getValue()){
            case "Car":
                CarService carService=CarService.getInstance();
                ObservableList<CarModel> carListViewModels=carService.getAllCars();
                carManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
                carModel.setCellValueFactory(new PropertyValueFactory<>("model"));
                carEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));
                carTransmission.setCellValueFactory(new PropertyValueFactory<>("transmission"));
                carDriveType.setCellValueFactory(new PropertyValueFactory<>("drive_type"));
                carVin.setCellValueFactory(new PropertyValueFactory<>("vin"));
                carPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                carDate.setCellValueFactory(new PropertyValueFactory<>("date_of_first_reg"));
                carMileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));
                carType.setCellValueFactory(new PropertyValueFactory<>("type"));
                carDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
                carOwner.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarModel, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CarModel, String> o) {
                        return new ReadOnlyObjectWrapper(o.getValue().getOwner().getOwner_name());
                    }
                });
                carPayment.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarModel, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CarModel, String> o) {
                        return new ReadOnlyObjectWrapper(o.getValue().getPayment().getPayment_type());
                    }
                });
                carTable.setItems(carListViewModels);
                switchDoublePane(checkCarPane, checkPane);
                break;
            case "CarService":
                CarServiceService carServiceService=CarServiceService.getInstance();
                ObservableList<CarServiceModel> carServiceModels=carServiceService.getAllCarServices();
                tbCarServiceCar.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarServiceModel, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CarServiceModel, String> o) {
                        return new ReadOnlyObjectWrapper(o.getValue().getCar().getVin());
                    }
                });
                tbCarServiceService.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarServiceModel, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CarServiceModel, String> o) {
                        return new ReadOnlyObjectWrapper(o.getValue().getService().getService_name());
                    }
                });
                tbCarServiceMechanic.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarServiceModel, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CarServiceModel, String> o) {
                        return new ReadOnlyObjectWrapper(o.getValue().getMechanic().getMechanic_name());
                    }
                });
                tbCarServicePrice.setCellValueFactory(new PropertyValueFactory<>("price_service"));
                switchDoublePane(checkCarServicePane, checkPane);
                break;
            case "Mechanic":
                MechanicService mechanicService=MechanicService.getInstance();
                ObservableList<MechanicModel> mechanicListViewModels=mechanicService.getAllMechanics();
                mechanicName.setCellValueFactory(new PropertyValueFactory<>("mechanic_name"));
                mechanicTable.setItems(mechanicListViewModels);
                switchDoublePane(checkMechanicPane, checkPane);
                break;
            case "Owner":
                OwnerService ownerService=OwnerService.getInstance();
                ObservableList<OwnerModel> ownerListViewModels=ownerService.getAllOwners();
                ownerName.setCellValueFactory(new PropertyValueFactory<>("owner_name"));
                ownerCarsOwned.setCellValueFactory(new PropertyValueFactory<>("number_of_cars_bought"));
                ownerTable.setItems(ownerListViewModels);
                switchDoublePane(checkOwnerPane, checkPane);
                break;
            case "Payment method":
                PaymentService paymentService=PaymentService.getInstance();
                ObservableList<PaymentModel> paymentListViewModels=paymentService.getAllPayments();
                paymentType.setCellValueFactory(new PropertyValueFactory<>("payment_type"));
                paymentTable.setItems(paymentListViewModels);
                switchDoublePane(checkPaymentPane, checkPane);
                break;
            case "Service":
                ServiceService serviceService=ServiceService.getInstance();
                ObservableList<ServiceModel> serviceListViewModels=serviceService.getAllServices();
                serviceName.setCellValueFactory(new PropertyValueFactory<>("service_name"));
                serviceType.setCellValueFactory(new PropertyValueFactory<>("service_type"));
                serviceTable.setItems(serviceListViewModels);
                switchDoublePane(checkServicePane, checkPane);
                break;
            case "null": showAlert(Alert.AlertType.WARNING, "Please choose an option from the combo box!"); //not working

        }
    }

    @FXML
    public void switchToAdd(ActionEvent actionEvent){
        switchDoublePane(addPane, logoPane);
    }

    @FXML
    public void switchToCheck(ActionEvent actionEvent){
        switchDoublePane(checkPane, logoPane);
    }

    public void switchDoublePane(Node paneName, Node frontPane){
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
        comboBoxCheckChoices.setItems(FXCollections.observableList(choices));
    }

    public void showAlert(Alert.AlertType type, String info){
        Alert alert=new Alert(type, info, ButtonType.OK);
        /*DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("Alerts.css");
        dialogPane.getStyleClass().add("Alert");*/
        alert.show();
    }
}
