package bg.tuvarna.sit.usp_cars.data.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mechanics")
public class Mechanic implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_mechanic",nullable = false)
    private int id_mechanic;

    @Column(name = "mechanic_name")
    private String mechanic_name;

    @OneToMany(mappedBy = "mechanic")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<CarService> carServices;

    public Mechanic() {
    }

    public Mechanic(String mechanic_name) {
        this.mechanic_name = mechanic_name;
    }

    public int getId_mechanic() {
        return id_mechanic;
    }

    public void setId_mechanic(int id_mechanic) {
        this.id_mechanic = id_mechanic;
    }

    public String getMechanic_name() {
        return mechanic_name;
    }

    public void setMechanic_name(String mechanic_name) {
        this.mechanic_name = mechanic_name;
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
        Mechanic mechanic = (Mechanic) o;
        return Objects.equals(mechanic_name, mechanic.mechanic_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mechanic_name);
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "mechanic_name='" + mechanic_name + '\'' +
                '}';
    }
}
