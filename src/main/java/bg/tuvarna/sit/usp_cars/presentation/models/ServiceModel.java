package bg.tuvarna.sit.usp_cars.presentation.models;

import java.util.Objects;

public class ServiceModel {
    private String service_name;
    private String service_type;

    public ServiceModel() {
    }

    public ServiceModel(String service_name, String service_type) {
        this.service_name = service_name;
        this.service_type = service_type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceModel that = (ServiceModel) o;
        return Objects.equals(service_name, that.service_name) && Objects.equals(service_type, that.service_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service_name, service_type);
    }

    @Override
    public String toString() {
        return "ServiceModel{" +
                "service_name='" + service_name + '\'' +
                ", service_type='" + service_type + '\'' +
                '}';
    }
}
