package bg.tuvarna.sit.usp_cars.presentation.models;

import bg.tuvarna.sit.usp_cars.data.entities.Car;
import bg.tuvarna.sit.usp_cars.data.entities.Mechanic;
import bg.tuvarna.sit.usp_cars.data.entities.Service;

import java.util.Objects;

public class CarServiceModel {
    private Car car;
    private Service service;
    private Mechanic mechanic;
    private Double price_service;

    public CarServiceModel() {
    }

    public CarServiceModel(Car car, Service service, Mechanic mechanic, Double price_service) {
        this.car = car;
        this.service = service;
        this.mechanic = mechanic;
        this.price_service = price_service;
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
        CarServiceModel that = (CarServiceModel) o;
        return Objects.equals(car, that.car) && Objects.equals(service, that.service) && Objects.equals(mechanic, that.mechanic) && Objects.equals(price_service, that.price_service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car, service, mechanic, price_service);
    }

    @Override
    public String toString() {
        return "car" + car +
                ", service=" + service;
    }
}
