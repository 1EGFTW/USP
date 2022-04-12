package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.*;
import bg.tuvarna.sit.usp_cars.data.entities.CarService;
import bg.tuvarna.sit.usp_cars.data.repositories.CarServiceRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.CarServiceModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceServiceTest {
    private CarServiceService carServiceService;
    private CarServiceRepository carServiceRepository;
    private Car car;
    private Service service;
    private Mechanic mechanic;
    private Owner owner;
    private Payment payment;
    private CarServiceModel carServiceModel;
    private CarService carService;

    @BeforeEach
    void setUp() {
        carServiceRepository=CarServiceRepository.getInstance();
        carServiceService=CarServiceService.getInstance();
        service=new Service("1","1");
        mechanic=new Mechanic("1");
        LocalDate ld = LocalDate.now();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
        Date date = c.getTime();
        owner=new Owner("1",0);
        payment=new Payment("1");
        car=new Car("MB","1","1","1","1","1",1.0,date,
                0,"1",1.0,owner,payment);
        carServiceModel=new CarServiceModel(car,service,mechanic,1.0);
        carService=new CarService(car,service,mechanic,1.0);
    }

    @Test
    void getAllCarServices() {
        ObservableList<CarServiceModel> before=carServiceService.getAllCarServices();
        assertEquals(before.size(),carServiceService.getAllCarServices().size());
    }

    @Test
    void findCarService() {
        assertNull(carServiceService.findCarService(carServiceModel));
    }

    @Test
    void findByCar() {
        assertNull(carServiceService.findByCar(car));
    }

    @Test
    void findByServiceName() {
        assertNull(carServiceService.findByServiceName(service));
    }

    @Test
    void findByMechanicName() {
        assertNull(carServiceService.findByMechanicName(mechanic));
    }

    @Test
    void addCarService() {
        assertFalse(carServiceService.addCarService(carServiceModel));//zashtoto tezi owner i service i car gi nqma w bazata
    }

    @Test
    void updateCarService() {
        assertFalse(carServiceService.updateCarService(carServiceModel));
    }

    @Test
    void deleteCarService() {
        assertFalse(carServiceService.deleteCarService(carServiceModel));
    }
}