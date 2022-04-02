package bg.tuvarna.sit.usp_cars.data.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class CarService implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_car_service",nullable = false)
    private int id_car_service;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="id_car")
    private Car car;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="id_service")
    private Service service;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="id_mechanic")
    private Mechanic mechanic;

    @Column(name = "price_service")
    private Double price_service;


    public CarService() {
    }

    public CarService(Car car, Service service, Mechanic mechanic, Double price_service) {
        this.car = car;
        this.service = service;
        this.mechanic = mechanic;
        this.price_service = price_service;
    }

    public int getId_car_service() {
        return id_car_service;
    }

    public void setId_car_service(int id_car_service) {
        this.id_car_service = id_car_service;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Double getPrice_service() {
        return price_service;
    }

    public void setPrice_service(Double price_service) {
        this.price_service = price_service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarService that = (CarService) o;
        return Objects.equals(car, that.car) && Objects.equals(service, that.service) && Objects.equals(mechanic, that.mechanic) && Objects.equals(price_service, that.price_service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car, service, mechanic, price_service);
    }

}
