package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Service;
import bg.tuvarna.sit.usp_cars.data.repositories.ServiceRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.ServiceModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceServiceTest {
    private ServiceService serviceService;
    private ServiceModel serviceModel;
    private Service service;
    private ServiceRepository serviceRepository;

    @BeforeEach
    void setUp() {
        service=new Service("1","1");
        serviceModel=new ServiceModel("1","1");
        serviceRepository=ServiceRepository.getInstance();
        serviceService=ServiceService.getInstance();
    }

    @Test
    void getAllServices() {
        ObservableList<ServiceModel> before=serviceService.getAllServices();
        serviceRepository.save(service);
        ObservableList<ServiceModel> after=serviceService.getAllServices();
        assertNotEquals(before,after);
        serviceRepository.delete(service);

    }

    @Test
    void findService() {
        assertNull(serviceService.findService(serviceModel));
    }

    @Test
    void addService() {
        assertTrue(serviceService.addService(serviceModel));
        service=serviceService.findService(serviceModel);
        serviceRepository.delete(service);
    }

    @Test
    void findServiceByName() {
        assertNull(serviceService.findServiceByName(service.getService_name()));
    }

    @Test
    void findServiceByType() {
        assertNull(serviceService.findServiceByType(service.getService_type()));
    }

    @Test
    void updateService() {
        assertFalse(serviceService.updateService(serviceModel));
    }

    @Test
    void deleteService() {
        assertFalse(serviceService.deleteService(serviceModel));
    }
}