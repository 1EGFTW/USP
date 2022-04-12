package bg.tuvarna.sit.usp_cars.presentation.models;

import bg.tuvarna.sit.usp_cars.data.entities.Owner;
import bg.tuvarna.sit.usp_cars.data.entities.Payment;

import java.util.Date;
import java.util.Objects;

public class CarModel {
    private String manufacturer;
    private String model;
    private String engine;
    private String transmission;
    private String drive_type;
    private String vin;
    private Double price;
    private Date date_of_first_reg;
    private int mileage;
    private String type;
    private Double discount;
    private Owner owner;
    private Payment payment;

    public CarModel() {
    }

    public CarModel(String manufacturer, String model, String engine, String transmission, String drive_type,
                    String vin, Double price, Date date_of_first_reg, int mileage, String type,
                    Double discount, Owner owner, Payment payment) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.engine = engine;
        this.transmission = transmission;
        this.drive_type = drive_type;
        this.vin = vin;
        this.price = price;
        this.date_of_first_reg = date_of_first_reg;
        this.mileage = mileage;
        this.type = type;
        this.discount = discount;
        this.owner = owner;
        this.payment = payment;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDrive_type() {
        return drive_type;
    }

    public void setDrive_type(String drive_type) {
        this.drive_type = drive_type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate_of_first_reg() {
        return date_of_first_reg;
    }

    public void setDate_of_first_reg(Date date_of_first_reg) {
        this.date_of_first_reg = date_of_first_reg;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return mileage == carModel.mileage && Objects.equals(manufacturer, carModel.manufacturer) && Objects.equals(model, carModel.model) && Objects.equals(vin, carModel.vin) && Objects.equals(date_of_first_reg, carModel.date_of_first_reg) && Objects.equals(owner, carModel.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, vin, date_of_first_reg, mileage, owner);
    }

    @Override
    public String toString() {
        return "Car: " +
                manufacturer + '|' +
                " " + model + '|' +
                " " + vin + '|' +
                ", price: " + price + '|' +
                ", owner: " + owner;
    }
}
