package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Owner;
import bg.tuvarna.sit.usp_cars.data.repositories.OwnerRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.OwnerModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceTest {
    private OwnerRepository ownerRepository;
    private OwnerService ownerService;
    private Owner owner;
    private OwnerModel ownerModel;

    @BeforeEach
    void setUp() {
        ownerRepository=OwnerRepository.getInstance();
        ownerService=OwnerService.getInstance();
        owner=new Owner("1",0);
        ownerModel=new OwnerModel("1",0);
    }

    @Test
    void getAllOwners() {
        ObservableList<OwnerModel> before= ownerService.getAllOwners();
        ownerRepository.save(owner);
        ObservableList<OwnerModel> after= ownerService.getAllOwners();
        assertNotEquals(before,after);
        ownerRepository.delete(owner);
    }

    @Test
    void findOwner() {
        assertNull(ownerService.findOwner(ownerModel));
    }

    @Test
    void addOwner() {
        assertTrue(ownerService.addOwner(ownerModel));
        owner=ownerService.findOwner(ownerModel);
        ownerRepository.delete(owner);
    }

    @Test
    void findOwnerByName() {
        assertNull(ownerService.findOwnerByName(ownerModel.getOwner_name()));
    }

    @Test
    void updateOwner() {
        assertFalse(ownerService.updateOwner(ownerModel));
    }

    @Test
    void deleteOwner() {
        assertFalse(ownerService.deleteOwner(ownerModel));
    }
}