package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Car;
import bg.tuvarna.sit.usp_cars.data.entities.Service;
import bg.tuvarna.sit.usp_cars.data.repositories.CarRepository;
import bg.tuvarna.sit.usp_cars.data.repositories.OwnerRepository;
import bg.tuvarna.sit.usp_cars.data.repositories.ServiceRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.CarModel;
import bg.tuvarna.sit.usp_cars.presentation.models.OwnerModel;
import bg.tuvarna.sit.usp_cars.presentation.models.ServiceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class CarService {
    private static final Logger log=Logger.getLogger(CarService.class);
    private final CarRepository repository= CarRepository.getInstance();
    public static CarService getInstance() {
        return CarService.CarServiceHolder.INSTANCE;
    }
    private static class CarServiceHolder {
        public static final CarService INSTANCE = new CarService();
    }

    public ObservableList<CarModel> getAllCars() {
        List<Car> cars=repository.getAll();

        return FXCollections.observableList(
                cars.stream().map(c -> new CarModel(
                        c.getManufacturer(),
                        c.getModel(),
                        c.getEngine(),
                        c.getTransmission(),
                        c.getDrive_type(),
                        c.getVin(),
                        c.getPrice(),
                        c.getDate_of_first_reg(),
                        c.getMileage(),
                        c.getType(),
                        c.getDiscount(),
                        c.getOwner(),
                        c.getPayment()
                )).collect(Collectors.toList()));
    }

    public Car findCar(CarModel carModel){
        Car car=new Car(carModel.getVin());
        for(Car c: repository.getAll()){
            if(car.getVin().equals(c.getVin())){
                return c;
            }
        }
        return null;
    }

    public boolean addCar(CarModel carModel){
        if(findCar(carModel)!=null) {
            log.info("Car already exists!\n");
            return false;
        }else{
            try{
                Car car=new Car(carModel.getManufacturer(),carModel.getModel(),
                        carModel.getEngine(),carModel.getTransmission(), carModel.getDrive_type(), carModel.getVin(),
                        carModel.getPrice(), carModel.getDate_of_first_reg(),carModel.getMileage(), carModel.getType(),
                        carModel.getDiscount(), carModel.getOwner(), carModel.getPayment());
                repository.save(car);
                car.getOwner().setNumber_of_cars_bought(car.getOwner().getNumber_of_cars_bought()+1);
                OwnerRepository.getInstance().update(car.getOwner());
                log.info("Car added successfully!\n");
                return true;
            }catch(Exception e){
                log.error("Error adding car!\n");
                e.printStackTrace();
                return false;
            }
        }
    }

    public Car findCarByVin(String vin){
        for(Car c: repository.getAll()){
            if(c.getVin().equals(vin))
                return c;
        }
        return null;
    }
    public boolean updateCar(CarModel carModel,OwnerModel ownerModel,Double newPrice,int mileage){
        if(findCarByVin(carModel.getVin())==null){
            log.error("No such car!");
            return false;
        }
        try{
            OwnerService ownerService=OwnerService.getInstance();
            Car car=findCarByVin(carModel.getVin());
            if(!ownerService.findOwner(ownerModel).equals(car.getOwner())){
                car.getOwner().setNumber_of_cars_bought(car.getOwner().getNumber_of_cars_bought()-1);
                car.setOwner(ownerService.findOwner(ownerModel));
                car.getOwner().setNumber_of_cars_bought(car.getOwner().getNumber_of_cars_bought()+1);
            }
            car.setMileage(mileage);
            car.setPrice(newPrice);
            OwnerRepository ownerRepository=OwnerRepository.getInstance();
            ownerRepository.update(car.getOwner());
            repository.update(car);
            log.info("Car updated successfully!");
            log.info("Owner's cars updated successfully!");
            return true;
        }catch(Exception e){
            log.error("Error updating car!");
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteCar(CarModel carModel){
        if(findCarByVin(carModel.getVin())==null){
            log.error("No such car!");
            return false;
        }
        try{
            Car car=findCarByVin(carModel.getVin());
            car.getOwner().setNumber_of_cars_bought(car.getOwner().getNumber_of_cars_bought()-1);
            OwnerRepository ownerRepository=OwnerRepository.getInstance();
            ownerRepository.update(car.getOwner());
            repository.delete(car);
            log.info("Car deleted successfully!");
            return true;
        }catch(Exception e){
            log.error("Error deleting car!");
            e.printStackTrace();
            return false;
        }
    }
}
