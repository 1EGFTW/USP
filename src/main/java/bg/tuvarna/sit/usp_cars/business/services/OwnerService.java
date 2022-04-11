package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Owner;
import bg.tuvarna.sit.usp_cars.data.entities.User;
import bg.tuvarna.sit.usp_cars.data.repositories.OwnerRepository;
import bg.tuvarna.sit.usp_cars.data.repositories.UserRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.OwnerModel;
import bg.tuvarna.sit.usp_cars.presentation.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.security.spec.ECField;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerService {
    private static final Logger log=Logger.getLogger(OwnerService.class);
    private final OwnerRepository repository= OwnerRepository.getInstance();
    public static OwnerService getInstance() {
        return OwnerService.OwnerServiceHolder.INSTANCE;
    }
    private static class OwnerServiceHolder {
        public static final OwnerService INSTANCE = new OwnerService();
    }

    public ObservableList<OwnerModel> getAllOwners() {
        List<Owner> owners=repository.getAll();

        return FXCollections.observableList(
                owners.stream().map(o -> new OwnerModel(
                        o.getOwner_name(),
                        o.getNumber_of_cars_bought()
                )).collect(Collectors.toList()));
    }

    public Owner findOwner(OwnerModel ownerModel){
        Owner owner=new Owner(ownerModel.getOwner_name(),ownerModel.getNumber_of_cars_bought());
        for(Owner o:repository.getAll()){
            if(o.equals(owner)){
                return o;
            }
        }
        return null;
    }
    public boolean addOwner(OwnerModel ownerModel){
        if(findOwner(ownerModel)!=null){
            log.info("Owner "+ownerModel.getOwner_name()+" already exists!\n");
            return false;
        }else{
            try{
                Owner owner=new Owner(ownerModel.getOwner_name(),ownerModel.getNumber_of_cars_bought());
                repository.save(owner);
                log.info("Owner "+ownerModel.getOwner_name()+" added!\n");
                return true;
            }catch(Exception e){
                log.error("Error adding owner!");
                e.printStackTrace();
                return false;
            }
        }
    }
    public Owner findOwnerByName(String name){
        for(Owner o: repository.getAll()){
            if(o.getOwner_name().equals(name)){
                return o;
            }
        }
        return null;
    }
    public boolean updateOwner(OwnerModel ownerModel){
        if(findOwnerByName(ownerModel.getOwner_name())==null){
            log.error("No such owner!");
            return false;
        }
        try{
            Owner owner=findOwnerByName(ownerModel.getOwner_name());
            owner.setNumber_of_cars_bought(ownerModel.getNumber_of_cars_bought());
            repository.update(owner);
            log.info("Successfully updated owner "+owner.getOwner_name()+"!");
            return true;
        }catch (Exception e){
            log.error("Error updating owner!");
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteOwner(OwnerModel ownerModel){
        if(findOwner(ownerModel)==null){
            log.error("No such owner!");
            return false;
        }
        try{
            Owner owner=findOwner(ownerModel);
            repository.delete(owner);
            log.info("Successfully deleted owner "+owner.getOwner_name()+"!");
            return true;
        }catch (Exception e){
            log.error("Error deleting owner!");
            e.printStackTrace();
            return false;
        }
    }
}
