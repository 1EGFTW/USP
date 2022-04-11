package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Payment;
import bg.tuvarna.sit.usp_cars.data.entities.Service;
import bg.tuvarna.sit.usp_cars.data.repositories.PaymentRepository;
import bg.tuvarna.sit.usp_cars.data.repositories.ServiceRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.PaymentModel;
import bg.tuvarna.sit.usp_cars.presentation.models.ServiceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ServiceService {
    private static final Logger log=Logger.getLogger(ServiceService.class);
    private final ServiceRepository repository= ServiceRepository.getInstance();
    public static ServiceService getInstance() {
        return ServiceService.ServiceServiceHolder.INSTANCE;
    }
    private static class ServiceServiceHolder {
        public static final ServiceService INSTANCE = new ServiceService();
    }

    public ObservableList<ServiceModel> getAllServices() {
        List<Service> services=repository.getAll();

        return FXCollections.observableList(
                services.stream().map(s -> new ServiceModel(
                        s.getService_name(),
                        s.getService_type()
                )).collect(Collectors.toList()));
    }

    public Service findService(ServiceModel serviceModel){
        Service service=new Service(serviceModel.getService_name(), serviceModel.getService_type());
        for(Service s: repository.getAll()){
            if(service.equals(s))
                return s;
        }
        return null;
    }

    public boolean addService(ServiceModel serviceModel){
        if(findService(serviceModel)!=null){
            log.info("Service already exists!\n");
            return false;
        }else{
            try{
                Service service=new Service(serviceModel.getService_name(), serviceModel.getService_type());
                repository.save(service);
                log.info("Service "+service.getService_name()+" added!\n");
                return true;
            }catch(Exception e){
                log.error("Error adding service!");
                e.printStackTrace();
                return false;
            }
        }
    }
    public Service findServiceByName(String name){
        for(Service s:repository.getAll()){
            if(s.getService_name().equals(name))
                return s;
        }
        return null;
    }
    public Service findServiceByType(String type){
        for(Service s:repository.getAll()){
            if(s.getService_type().equals(type))
                return s;
        }
        return null;
    }
    public boolean updateService(ServiceModel serviceModel){
        if(findServiceByName(serviceModel.getService_name())==null){
            log.error("No such service");
            return false;
        }
        try{
            Service service=findServiceByName(serviceModel.getService_name());
            service.setService_type(serviceModel.getService_type());
            repository.update(service);
            log.info("Service "+service.getService_name()+" updated!");
            return true;
        }catch(Exception e){
            log.error("Error updating service!");
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteService(ServiceModel serviceModel){
        if(findService(serviceModel)==null){
            log.error("No such service");
            return false;
        }
        try{
            Service service=findService(serviceModel);
            repository.delete(service);
            log.info("Service "+service.getService_name()+" deleted!");
            return true;
        }catch(Exception e){
            log.error("Error deleting service!");
            e.printStackTrace();
            return false;
        }
    }
}




