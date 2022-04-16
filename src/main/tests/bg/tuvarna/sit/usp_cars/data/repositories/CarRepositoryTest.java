package bg.tuvarna.sit.usp_cars.data.repositories;

import bg.tuvarna.sit.usp_cars.data.entities.Car;
import bg.tuvarna.sit.usp_cars.data.entities.Owner;
import bg.tuvarna.sit.usp_cars.data.entities.Payment;
import bg.tuvarna.sit.usp_cars.presentation.models.CarModel;
import bg.tuvarna.sit.usp_cars.presentation.models.OwnerModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {
    private CarRepository carRepository;
    private OwnerRepository ownerRepository;
    private PaymentRepository paymentRepository;
    private Car car,car1;

    @BeforeEach
    void setUp() {
        this.carRepository=CarRepository.getInstance();
        this.ownerRepository=OwnerRepository.getInstance();
        this.paymentRepository=PaymentRepository.getInstance();
        LocalDate ld = LocalDate.now();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
        Date date = c.getTime();
        Owner owner=new Owner("1",0);
        Payment payment=new Payment("1");
        car=new Car("MB","1","1","1","1","1",1.0,date,
                0,"1",1.0,owner,payment);
        car1=new Car("MB","1","1","1","1","1",1.0,date,
                0,"1",1.0,owner,payment);
        ownerRepository.save(car.getOwner());
        paymentRepository.save(car.getPayment());
    }

    @AfterEach
    void tearDown() {
        ownerRepository.delete(car.getOwner());
        paymentRepository.delete(car.getPayment());
    }

    @Test
    void save() {
        List<Car> all=carRepository.getAll();
        carRepository.save(car);
        assertNotEquals(all,carRepository.getAll());
        carRepository.delete(car);
    }

    @Test
    void update() {
        carRepository.save(car);
        List<Car> all=carRepository.getAll();
        car.setPrice(999.0);
        carRepository.update(car);
        assertNotEquals(all,carRepository.getAll());
        carRepository.delete(car);
    }

    @Test
    void delete() {
        carRepository.save(car);
        List<Car> all=carRepository.getAll();
        carRepository.delete(car);
        assertNotEquals(all,carRepository.getAll());
    }


    @Test
    void getAll() {
        carRepository.save(car);
        List<Car> all=carRepository.getAll();
        carRepository.save(car1);
        List<Car> after=carRepository.getAll();
        assertNotEquals(all,after);
        carRepository.delete(car);
        carRepository.delete(car1);
    }
}