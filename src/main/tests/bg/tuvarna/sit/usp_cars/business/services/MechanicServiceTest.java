package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Mechanic;
import bg.tuvarna.sit.usp_cars.data.repositories.MechanicRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.MechanicModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MechanicServiceTest {
    private MechanicService mechanicService;
    private MechanicRepository mechanicRepository;
    private Mechanic mechanic;
    private MechanicModel mechanicModel;

    @BeforeEach
    void setUp() {
        mechanicService=MechanicService.getInstance();
        mechanicRepository=MechanicRepository.getInstance();
        mechanic=new Mechanic("1");
        mechanicModel=new MechanicModel("1");
    }

    @Test
    void getAllMechanics() {
        ObservableList<MechanicModel> before=mechanicService.getAllMechanics();
        mechanicRepository.save(mechanic);
        ObservableList<MechanicModel> after=mechanicService.getAllMechanics();
        assertNotEquals(before,after);
        mechanicRepository.delete(mechanic);
    }

    @Test
    void findMechanic() {
        assertNull(mechanicService.findMechanic(mechanicModel));
    }

    @Test
    void findMechanicByName() {
        assertNull(mechanicService.findMechanicByName(mechanicModel.getMechanic_name()));
    }

    @Test
    void addMechanic() {
        assertTrue(mechanicService.addMechanic(mechanicModel));
        mechanic=mechanicService.findMechanic(mechanicModel);
        mechanicRepository.delete(mechanic);
    }

    @Test
    void deleteMechanic() {
        assertFalse(mechanicService.deleteMechanic(mechanicModel));
    }
}