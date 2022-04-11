package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Mechanic;
import bg.tuvarna.sit.usp_cars.data.entities.Owner;
import bg.tuvarna.sit.usp_cars.data.entities.Payment;
import bg.tuvarna.sit.usp_cars.data.repositories.MechanicRepository;
import bg.tuvarna.sit.usp_cars.data.repositories.PaymentRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.MechanicModel;
import bg.tuvarna.sit.usp_cars.presentation.models.OwnerModel;
import bg.tuvarna.sit.usp_cars.presentation.models.PaymentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.security.spec.ECField;
import java.util.List;
import java.util.stream.Collectors;

public class MechanicService {
    private static final Logger log=Logger.getLogger(MechanicService.class);
    private final MechanicRepository repository= MechanicRepository.getInstance();
    public static MechanicService getInstance() {
        return MechanicService.MechanicServiceHolder.INSTANCE;
    }
    private static class MechanicServiceHolder {
        public static final MechanicService INSTANCE = new MechanicService();
    }

    public ObservableList<MechanicModel> getAllMechanics() {
        List<Mechanic> mechanics=repository.getAll();

        return FXCollections.observableList(
                mechanics.stream().map(m -> new MechanicModel(
                        m.getMechanic_name()
                )).collect(Collectors.toList()));
    }

    public Mechanic findMechanic(MechanicModel mechanicModel){
        Mechanic mechanic=new Mechanic(mechanicModel.getMechanic_name());
        for(Mechanic m: repository.getAll()){
            if(m.equals(mechanic))
                return m;
        }
        return null;
    }
    public Mechanic findMechanicByName(String name){
        for(Mechanic m: repository.getAll()){
            if(m.getMechanic_name().equals(name))
                return m;
        }
        return null;
    }
    public boolean addMechanic(MechanicModel mechanicModel){
        if(findMechanic(mechanicModel)!=null){
            log.info("Mechanic "+mechanicModel.getMechanic_name()+" already exists!\n");
            return false;
        }else{
            try{
                Mechanic mechanic=new Mechanic(mechanicModel.getMechanic_name());
                repository.save(mechanic);
                log.info("Mechanic "+mechanicModel.getMechanic_name()+" added!\n");
                return true;
            }catch(Exception e){
                log.error("Error adding mechanic!");
                e.printStackTrace();
                return false;
            }
        }
    }
   public boolean deleteMechanic(MechanicModel mechanicModel){
        if(findMechanic(mechanicModel)==null){
            log.error("No such mechanic!");
            return false;
        }
        try{
            Mechanic mechanic=findMechanic(mechanicModel);
            repository.delete(mechanic);
            log.info("Mechanic "+mechanic.getMechanic_name()+" deleted successfully!");
            return true;
        }catch(Exception e){
            log.error("Error deleting mechanic!");
            e.printStackTrace();
            return false;
        }
   }

}
