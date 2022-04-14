package bg.tuvarna.sit.usp_cars.data.entities;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_owner",nullable = false)
    private int id_owner;

    @Column(name="owner_name")
    private String owner_name;

    @Column(name="number_of_cars_bought")
    private int number_of_cars_bought;

    @OneToMany(mappedBy = "owner")
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    private Set<Car> cars=new HashSet<>();

    public Owner() {
    }

    public Owner(String owner_name, int number_of_cars_bought) {
        this.owner_name = owner_name;
        this.number_of_cars_bought = number_of_cars_bought;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
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

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner user = (Owner) o;
        return number_of_cars_bought == user.number_of_cars_bought && Objects.equals(owner_name, user.owner_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner_name, number_of_cars_bought);
    }

    @Override
    public String toString() {
        return owner_name + " |" +
                " cars owned=: " + number_of_cars_bought;
    }
}
