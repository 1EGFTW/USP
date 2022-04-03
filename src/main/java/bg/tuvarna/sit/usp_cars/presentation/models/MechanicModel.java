package bg.tuvarna.sit.usp_cars.presentation.models;

import java.util.Objects;

public class MechanicModel {
    private String mechanic_name;

    public MechanicModel(){}

    public MechanicModel(String mechanic_name){
        this.mechanic_name=mechanic_name;
    }

    public String getMechanic_name() {
        return mechanic_name;
    }

    public void setMechanic_name(String mechanic_name) {
        this.mechanic_name = mechanic_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MechanicModel that = (MechanicModel) o;
        return Objects.equals(mechanic_name, that.mechanic_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mechanic_name);
    }

    @Override
    public String toString() {
        return "MechanicModel{" +
                "mechanic_name='" + mechanic_name + '\'' +
                '}';
    }
}
