package bg.tuvarna.sit.usp_cars.presentation.models;

import java.util.Objects;

public class OwnerModel {
    private String owner_name;
    private int number_of_cars_bought;

    public OwnerModel() {
    }

    public OwnerModel(String owner_name, int number_of_cars_bought) {
        this.owner_name = owner_name;
        this.number_of_cars_bought = number_of_cars_bought;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public int getNumber_of_cars_bought() {
        return number_of_cars_bought;
    }

    public void setNumber_of_cars_bought(int number_of_cars_bought) {
        this.number_of_cars_bought = number_of_cars_bought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerModel that = (OwnerModel) o;
        return number_of_cars_bought == that.number_of_cars_bought && Objects.equals(owner_name, that.owner_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner_name, number_of_cars_bought);
    }

    @Override
    public String toString() {
        return "OwnerModel{" +
                "owner_name='" + owner_name + '\'' +
                ", number_of_cars_bought=" + number_of_cars_bought +
                '}';
    }
}
