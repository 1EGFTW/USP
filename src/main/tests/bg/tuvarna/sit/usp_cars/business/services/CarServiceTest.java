package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Car;
import bg.tuvarna.sit.usp_cars.data.entities.Owner;
import bg.tuvarna.sit.usp_cars.data.entities.Payment;
import bg.tuvarna.sit.usp_cars.data.repositories.CarRepository;
import bg.tuvarna.sit.usp_cars.data.repositories.OwnerRepository;
import bg.tuvarna.sit.usp_cars.data.repositories.PaymentRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.CarModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    private CarService carService;
    private CarRepository carRepository;
    private CarModel carModel;
    private Car car;
    private OwnerRepository ownerRepository;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        LocalDate ld = LocalDate.now();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
        Date date = c.getTime();
        Owner owner=new Owner("1",0);
        Payment payment=new Payment("1");
        car=new Car("MB","1","1","1","1","1",1.0,date,
                0,"1",1.0,owner,payment);
        carModel=new CarModel("MB","1","1","1","1","1",1.0,date,
                0,"1",1.0,owner,payment);
        carService=CarService.getInstance();
        carRepository=CarRepository.getInstance();
        ownerRepository=OwnerRepository.getInstance();
        paymentRepository=PaymentRepository.getInstance();
    }

    @Test
    void getAllCars() {
        ObservableList<CarModel> before=carService.getAllCars();
        ownerRepository.save(car.getOwner());
        paymentRepository.save(car.getPayment());
        carRepository.save(car);
        ObservableList<CarModel> after=carService.getAllCars();
        assertNotEquals(before,after);
        carRepository.delete(car);
        ownerRepository.delete(car.getOwner());
        paymentRepository.delete(car.getPayment());
    }

    @Test
    void findCar() {
        assertNull(carService.findCar(carModel));
    }

    @Test
    void addCar() {
        ownerRepository.save(car.getOwner());
        paymentRepository.save(car.getPayment());
        assertTrue(carService.addCar(carModel));
        car=carService.findCarByVin(carModel.getVin());
        carRepository.delete(car);
        ownerRepository.delete(car.getOwner());
        paymentRepository.delete(car.getPayment());
    }

    @Test
    void findCarByVin() {
        assertNull(carService.findCarByVin(carModel.getVin()));
    }

    @Test
    void updateCar() {
        assertFalse(carService.updateCar(carModel));
    }

    @Test
    void deleteCar() {
        assertFalse(carService.deleteCar(carModel));
    }
}