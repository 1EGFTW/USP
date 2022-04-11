package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Car;
import bg.tuvarna.sit.usp_cars.data.entities.CarService;
import bg.tuvarna.sit.usp_cars.data.entities.Mechanic;
import bg.tuvarna.sit.usp_cars.data.entities.Service;
import bg.tuvarna.sit.usp_cars.data.repositories.CarRepository;
import bg.tuvarna.sit.usp_cars.data.repositories.CarServiceRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.CarModel;
import bg.tuvarna.sit.usp_cars.presentation.models.CarServiceModel;
import bg.tuvarna.sit.usp_cars.presentation.models.MechanicModel;
import bg.tuvarna.sit.usp_cars.presentation.models.ServiceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class CarServiceService {
    private static final Logger log=Logger.getLogger(CarServiceService.class);
    private final CarServiceRepository repository= CarServiceRepository.getInstance();
    public static CarServiceService getInstance() {
        return CarServiceService.CarServiceServiceHolder.INSTANCE;
    }
    private static class CarServiceServiceHolder {
        public static final CarServiceService INSTANCE = new CarServiceService();
    }

    public ObservableList<CarServiceModel> getAllCarServices() {
        List<CarService> services=repository.getAll();

        return FXCollections.observableList(
                services.stream().map(c -> new CarServiceModel(
                        c.getCar(),
                        c.getService(),
                        c.getMechanic(),
                        c.getPrice_service()
                )).collect(Collectors.toList()));
    }
    public CarService findCarService(CarServiceModel carServiceModel){
        CarService carService=new CarService(carServiceModel.getCar(), carServiceModel.getService(),
                carServiceModel.getMechanic(), carServiceModel.getPrice_service());
        for(CarService cs: repository.getAll()){
            if(cs.equals(carService))
                return cs;
        }
        return null;
    }

    public CarService findByCarName(CarModel c){
        Car car= bg.tuvarna.sit.usp_cars.business.services.CarService.getInstance().findCar(c);
        List<CarService> carServices=repository.getAll();
        for(CarService cs:carServices){
            if(cs.getCar().equals(car))
                return cs;
        }
        return null;
    }
    public CarService findByServiceName(ServiceModel s){
        Service service=ServiceService.getInstance().findService(s);
        List<CarService> carServices=repository.getAll();
        for(CarService cs:carServices){
            if(cs.getService().equals(service))
                return cs;
        }
        return null;
    }
    public CarService findByMechanicName(MechanicModel m){
        Mechanic mechanic=MechanicService.getInstance().findMechanic(m);
        List<CarService> carServices=repository.getAll();
        for(CarService cs:carServices){
            if(cs.getMechanic().equals(mechanic))
                return cs;
        }
        return null;
    }

    public boolean addCarService(CarServiceModel carServiceModel){
        if(findCarService(carServiceModel)!=null){
            log.info("CarService already exists!\n");
            return false;
        }
        if(carServiceModel.getCar()==null || carServiceModel.getService()==null || carServiceModel.getMechanic()==null){
            log.error("Something is null!");
            return false;
        }

        CarService carService=new CarService(carServiceModel.getCar(), carServiceModel.getService(),
                carServiceModel.getMechanic(), carServiceModel.getPrice_service());
        try{
            repository.save(carService);
            log.info("CarService created!\n");
            return true;
        }catch(Exception e){
            log.error("Error creating CarService!\n");
            e.printStackTrace();
            return false;
        }
    }
}
