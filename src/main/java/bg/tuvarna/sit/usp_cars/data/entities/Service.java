package bg.tuvarna.sit.usp_cars.data.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_service",nullable = false)
    private int id_service;

    @Column(name = "service_name")
    private String service_name;

    @Column(name = "service_type")
    private String service_type;

    @OneToMany(mappedBy = "service")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<CarService> carServices;

    public Service() {
    }

    public Service(String service_name, String service_type) {
        this.service_name = service_name;
        this.service_type = service_type;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
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
        Service service = (Service) o;
        return Objects.equals(service_name, service.service_name) && Objects.equals(service_type, service.service_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service_name, service_type);
    }

    @Override
    public String toString() {
        return "Service{" +
                "service_name='" + service_name + '\'' +
                ", service_type='" + service_type + '\'' +
                '}';
    }
}
