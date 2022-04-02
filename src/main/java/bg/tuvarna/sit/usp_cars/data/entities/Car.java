package bg.tuvarna.sit.usp_cars.data.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_car",nullable = false)
    private int id_car;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "engine")
    private String engine;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "drive_type")
    private String drive_type;

    @Column(name = "vin")
    private String vin;

    @Column(name = "price")
    private Double price;

    @Column(name = "date_of_first_reg")
    private Date date_of_first_reg;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "type")
    private String type; //leka kola, jip,tir i tn

    @Column(name = "discount")
    private Double discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    @JoinColumn(name = "id_owner")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    @JoinColumn(name = "id_payment")
    private Payment payment;

    @OneToMany(mappedBy = "car")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<CarService> carServices;

    public Car() {
    }

    public Car(String manufacturer, String model, String engine, String transmission, String drive_type,
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

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
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

    public Set<CarService> getCarServices() {
        return carServices;
    }

    public void setCarServices(Set<CarService> carServices) {
        this.carServices = carServices;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(manufacturer, car.manufacturer) && Objects.equals(model, car.model) && Objects.equals(vin, car.vin) && Objects.equals(price, car.price) && Objects.equals(owner, car.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, vin, price, owner);
    }

    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", transmission='" + transmission + '\'' +
                ", drive_type='" + drive_type + '\'' +
                ", vin='" + vin + '\'' +
                ", price=" + price +
                ", date_of_first_reg=" + date_of_first_reg +
                ", mileage=" + mileage +
                ", type='" + type + '\'' +
                ", discount=" + discount +
                ", owner=" + owner +
                ", payment=" + payment +
                '}';
    }
}
